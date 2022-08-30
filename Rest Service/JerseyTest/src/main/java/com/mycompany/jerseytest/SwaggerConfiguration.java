package com.mycompany.jerseytest;

import io.swagger.jaxrs.config.BeanConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Pipis
 */
public class SwaggerConfiguration extends HttpServlet {

    //Αρχικοποίηση swagger  
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Search engine");
        beanConfig.setVersion("1.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/JerseyTest/webresources");
        beanConfig.setResourcePackage("com.mycompany.jerseytest");
        beanConfig.setScan(true);
        beanConfig.setDescription("Faster full text search engine");
    }

}
