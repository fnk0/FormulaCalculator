package com.gabilheri.formulacalculator.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Api(
        name = "themeBeanApi",
        version = "v1",
        resource = "themeBean",
        namespace = @ApiNamespace(
                ownerDomain = "backend.formulacalculator.gabilheri.com",
                ownerName = "backend.formulacalculator.gabilheri.com",
                packagePath = ""
        )
)
public class ThemeBeanEndpoint {
    // Make sure to add this endpoint to your web.xml file if this is a web application.
    private static final Logger logger = Logger.getLogger(ThemeBeanEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        ObjectifyService.register(ThemeBean.class);
    }

    @ApiMethod(
            name = "get",
            path = "themeBean/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public ThemeBean get(@Named("id") long id) throws NotFoundException {
        logger.info("Getting ThemeBean with ID: " + id);
        ThemeBean themeBean = ofy().load().type(ThemeBean.class).id(id).now();
        if (themeBean == null) {
            throw new NotFoundException("Could not find ThemeBean with ID: " + id);
        }
        return themeBean;
    }

    @ApiMethod(
            name = "insert",
            path = "themeBean",
            httpMethod = ApiMethod.HttpMethod.POST)
    public ThemeBean insert(ThemeBean themeBean) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that themeBean.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(themeBean).now();
        logger.info("Created ThemeBean with ID: " + themeBean.getId());

        return ofy().load().entity(themeBean).now();
    }

    @ApiMethod(
            name = "update",
            path = "themeBean/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public ThemeBean update(@Named("id") long id, ThemeBean themeBean) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(themeBean).now();
        logger.info("Updated ThemeBean: " + themeBean);
        return ofy().load().entity(themeBean).now();
    }

    @ApiMethod(
            name = "remove",
            path = "themeBean/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(ThemeBean.class).id(id).now();
        logger.info("Deleted ThemeBean with ID: " + id);
    }

    @ApiMethod(
            name = "list",
            path = "themeBean",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<ThemeBean> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<ThemeBean> query = ofy().load().type(ThemeBean.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<ThemeBean> queryIterator = query.iterator();
        List<ThemeBean> ThemeBeanList = new ArrayList<ThemeBean>(limit);
        while (queryIterator.hasNext()) {
            ThemeBeanList.add(queryIterator.next());
        }
        return CollectionResponse.<ThemeBean>builder().setItems(ThemeBeanList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(long id) throws NotFoundException {
        try {
            ofy().load().type(ThemeBean.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find ThemeBean with ID: " + id);
        }
    }
}