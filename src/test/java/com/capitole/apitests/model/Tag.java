package com.capitole.apitests.model;

import java.math.BigInteger;

public class Tag {
    private BigInteger id;
    private String name;

    public Tag() {
    }

    public Tag(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
