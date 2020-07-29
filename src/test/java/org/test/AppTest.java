package org.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.controller.LoginController;

/**
 *  Smoke test class to verify that context is getting created by Controller in Spring application context.
 */
@SpringBootTest
public class AppTest {

    @Autowired
    private LoginController controller;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}