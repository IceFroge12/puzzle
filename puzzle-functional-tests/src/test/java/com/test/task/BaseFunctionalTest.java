package com.test.task;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BaseFunctionalTest.TestContextConfiguration.class)
public class BaseFunctionalTest {


    @ComponentScan("com.test.task")
    public static class TestContextConfiguration {

    }
}
