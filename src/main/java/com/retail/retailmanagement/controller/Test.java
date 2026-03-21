package com.retail.retailmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.retailmanagement.repository.TestRepository;

@RestController
public class Test {
    private final TestRepository repo;

    @GetMapping("/api/v1/test")
    public String test() {
        return "API is working 🚀";
    }

    public Test(TestRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/api/v1/test2")
    public String test2() {
        repo.count(); // gọi DB
        return "Connected!";
    }
}
