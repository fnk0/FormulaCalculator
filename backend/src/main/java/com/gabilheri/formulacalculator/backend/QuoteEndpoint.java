package com.gabilheri.formulacalculator.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(name = "quoteEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.formulacalculator.gabilheri.com", ownerName = "backend.formulacalculator.gabilheri.com", packagePath=""))
public class QuoteEndpoint {

    // Make sure to add this endpoint to your web.xml file if this is a web application.

    private static final Logger LOG = Logger.getLogger(QuoteEndpoint.class.getName());

    /**
     * This method gets the <code>Quote</code> object associated with the specified <code>id</code>.
     * @param id The id of the object to be returned.
     * @return The <code>Quote</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getQuote")
    public Quote getQuote(@Named("id") Long id) {
        // Implement this function

        LOG.info("Calling getQuote method");
        return null;
    }

    /**
     * This inserts a new <code>Quote</code> object.
     * @param quote The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertQuote")
    public Quote insertQuote(Quote quote) {
        // Implement this function

        LOG.info("Calling insertQuote method");
        return quote;
    }
}