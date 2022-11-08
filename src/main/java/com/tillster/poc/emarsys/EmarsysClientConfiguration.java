package com.tillster.poc.emarsys;

import com.emn8.mobilem8.admin.IMobileM8AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Configuration
public class EmarsysClientConfiguration {

    private static final String EMARSYS_API_URL = "https://webhook.site/07321d3a-516e-4b05-8fdc-116eac0a77c1";
    private static final String TILLSTER_API_URL = "https://api-bk-kw-a.qa.tillster.com/mobilem8-admin/rest/";

    @Bean
    public EmarsysApiClient emarsysApiClient() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(EMARSYS_API_URL);
        target.register(CSVMessageBodyWriter.class);
        return WebResourceFactory.newResource(EmarsysApiClient.class, target);
    }

    @Bean
    public IMobileM8AdminService mobileM8AdminService() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(TILLSTER_API_URL);
        target.register(TillsterObjectMapperContextResolver.class);
        return WebResourceFactory.newResource(IMobileM8AdminService.class, target);
    }
}
