package com.capitole.apitests.api.steps;

import com.capitole.apitests.frameworkauto.utils.TestContext;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class CommonSteps {
    private TestContext testContext;
    private Response response;

    public CommonSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @And("The status code should be {int}")
    public void theStatusCodeShouldBe(final int statusCode) {
        response = (Response) testContext.getScenarioContext().getContext("RESPONSE");
        Assertions.assertEquals(statusCode, response.statusCode());
    }
}
