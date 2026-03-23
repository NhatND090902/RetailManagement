package com.retail.retailmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.retailmanagement.repository.TestRepository;

@RestController
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);
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
        try {
            long count = repo.count(); // gọi DB
            logger.info("Connected to DB, total records: {}", count);
            return "Connected!";
        } catch (Exception e) {
            logger.error("Error when connecting to DB", e);
            return "DB Error!";
        }
    }
}
