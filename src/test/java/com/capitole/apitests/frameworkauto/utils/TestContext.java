package com.capitole.apitests.frameworkauto.utils;

public class TestContext {
    private ScenarioContext scenarioContext;

    public TestContext() {
        this.scenarioContext = new ScenarioContext();
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    public void setScenarioContext(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }
}
