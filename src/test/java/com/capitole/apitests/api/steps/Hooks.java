package com.capitole.apitests.api.steps;

import com.capitole.apitests.frameworkauto.utils.RestUtils;
import io.cucumber.java.Before;

public class Hooks {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/";
    @Before
    public void setupBaseUri() {
        RestUtils.setBaseUri(BASE_URI);
    }
}
