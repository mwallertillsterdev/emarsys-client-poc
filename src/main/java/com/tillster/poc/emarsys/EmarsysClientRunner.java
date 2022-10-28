package com.tillster.poc.emarsys;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@Component
public class EmarsysClientRunner implements CommandLineRunner {

    @Inject
    EmarsysApiClient emarsysApiClient;

    @Override
    public void run(String... args) throws Exception {

        List sampleObjects = Arrays.asList(new SampleObject[]{
                SampleObject.builder()
                        .fieldOne("one test one")
                        .fieldTwo("one test two")
                        .fieldThree("one test three")
                        .build(),
                SampleObject.builder()
                        .fieldOne("two test one")
                        .fieldTwo("two test two")
                        .fieldThree("two test three")
                        .build(),
                SampleObject.builder()
                        .fieldOne("three test one")
                        .fieldTwo("three test two")
                        .fieldThree("three test three")
                        .build()
        });

        emarsysApiClient.createOrder(sampleObjects);
    }
}
