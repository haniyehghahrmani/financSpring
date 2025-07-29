package org.java.financespring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    @GetMapping("/log-test")
    public String logTest() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("✅ درخواست log-test دریافت شد.");
        return "Hello Kafka";
    }

    @GetMapping("/crash")
    public String crash() {
        throw new RuntimeException("💥 تست Exception برای بررسی لاگ");
    }

}

