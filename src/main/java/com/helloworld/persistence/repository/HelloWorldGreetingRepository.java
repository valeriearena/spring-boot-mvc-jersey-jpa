package com.helloworld.persistence.repository;

import com.helloworld.persistence.entity.HelloWorldGreetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by valerie on 4/29/17.
 */
public interface HelloWorldGreetingRepository extends JpaRepository<HelloWorldGreetingEntity, Long> { }