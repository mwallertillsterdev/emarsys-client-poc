package com.tillster.poc.emarsys;

import com.emn8.mobilem8.mapping.Coordinate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.text.SimpleDateFormat;

@Provider
public class TillsterObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public TillsterObjectMapperContextResolver() {
        this.mapper = createMobileM8ObjectMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }
    private static final String DATE_FORMAT_MOBILE_M8 = "yyyy-MM-dd'T'HH:mm:ss.S";

    private ObjectMapper createMobileM8ObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
                .enable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS)
                .setDateFormat(new SimpleDateFormat(DATE_FORMAT_MOBILE_M8));

        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
        // mapper.setAnnotationIntrospector(AnnotationIntrospector.pair(new TillsterJaxbAnnotationInspector(), secondary));

        SimpleModule simpleModule = new SimpleModule();

        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                // .addMixIn(Coordinate.class, CoordinatesMixIn.class)
                .registerModule(simpleModule)
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        return mapper;
    }
}
