package com.example.demo;
import java.io.Serializable;
import java.util.List;

@lombok.Data
public class ResponseApi implements Serializable {
    private String language;
    private int occurence;
    private List<Data> repos;
}
