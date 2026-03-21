package com.retail.retailmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.retailmanagement.entity.Test;

public interface TestRepository extends JpaRepository<Test, Long> {

}
