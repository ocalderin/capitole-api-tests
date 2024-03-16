package com.capitole.apitests.runners;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@SuiteDisplayName("Api Tests")
@IncludeEngines("cucumber")
@SelectClasspathResource("api/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.capitole.apitests.api.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:reports/cucumber-report/api-report.json, " +
        "html:reports/cucumber-report/api-report.html, timeline:reports/cucumber-report/api-timeline-report")
public class RunnerApiTests {
}
