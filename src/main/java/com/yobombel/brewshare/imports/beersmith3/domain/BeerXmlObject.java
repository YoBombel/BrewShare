package com.yobombel.brewshare.imports.beersmith3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public abstract class BeerXmlObject {

    @JsonIgnore
    private final HashSet<String> endReaderLoopElements;
    @JsonIgnore
    private final Map<String, Consumer<String>> xmlElementsDictionary;

    protected BeerXmlObject() {
        this.endReaderLoopElements = new HashSet<>();
        this.xmlElementsDictionary = new HashMap<>();
    }


    public Set<String> getEndReaderLoopElements() {
        return endReaderLoopElements;
    }

    public Map<String, Consumer<String>> getXmlElementsDictionary() {
        return xmlElementsDictionary;
    }
}