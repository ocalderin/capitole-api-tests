package com.capitole.apitests.frameworkauto.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestUtils {
    private static RequestSpecification requestSpecification;

    public static void setBaseUri(final String baseUri) {
        RestAssured.baseURI = baseUri;
        requestSpecification = new RequestSpecBuilder().setBaseUri(baseUri)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter()).build();
    }

    public static Response doGet(final String path) {
        return given().spec(requestSpecification).get(path).andReturn();
    }

    public static Response doPost(final String path, final Object body) {
        return given().spec(requestSpecification).body(body).post(path);
    }
}
