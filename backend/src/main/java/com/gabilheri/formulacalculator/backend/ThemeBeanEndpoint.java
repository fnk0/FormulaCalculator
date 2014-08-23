package com.gabilheri.formulacalculator.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(name = "themeBeanEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.formulacalculator.gabilheri.com", ownerName = "backend.formulacalculator.gabilheri.com", packagePath=""))
public class ThemeBeanEndpoint {

    // Make sure to add this endpoint to your web.xml file if this is a web application.

    private static final Logger LOG = Logger.getLogger(ThemeBeanEndpoint.class.getName());

    /**
     * This method gets the <code>ThemeBean</code> object associated with the specified <code>id</code>.
     * @param id The id of the object to be returned.
     * @return The <code>ThemeBean</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getThemeBean")
    public ThemeBean getThemeBean(@Named("id") Long id) {
        // Implement this function

        LOG.info("Calling getThemeBean method");
        return null;
    }

    /**
     * This inserts a new <code>ThemeBean</code> object.
     * @param themeBean The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertThemeBean")
    public ThemeBean insertThemeBean(ThemeBean themeBean) {
        // Implement this function

        LOG.info("Calling insertThemeBean method");
        return themeBean;
    }
}