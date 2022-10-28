package com.tillster.poc.emarsys;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Configuration
public class EmarsysClientConfiguration {

    private static final String EMARSYS_API_URL = "https://webhook.site/07321d3a-516e-4b05-8fdc-116eac0a77c1";

    @Bean
    public EmarsysApiClient emarsysApiClient() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(EMARSYS_API_URL);
        target.register(CSVMessageBodyWriter.class);
        return WebResourceFactory.newResource(EmarsysApiClient.class, target);
    }
}
