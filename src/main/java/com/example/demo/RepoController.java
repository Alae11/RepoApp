package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@RestController
public class RepoController {
    @Autowired
    private Service service;

    @GetMapping("/repos")
    public List<ResponseApi> getRepos()  {
        return service.getResponseApi();
    }

}
