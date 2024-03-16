package com.capitole.apitests.frameworkauto.utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private Map<String, Object> context;

    public ScenarioContext() {
        context = new HashMap<>();
    }

    public void setContext(final String key, final Object value) {
        context.put(key, value);
    }

    public Object getContext(final String key) {
        return context.get(key);
    }

}
