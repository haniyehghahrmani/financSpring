package org.java.financespring.configuration;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.github.danielwegener.logback.kafka.KafkaAppender;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class KafkaAppenderInitializer {

    public static void initKafkaAppender(ApplicationContext context) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);

        KafkaAppender kafkaAppender = context.getBean(KafkaAppender.class);
        kafkaAppender.start(); // حالا فعالش می‌کنیم

        rootLogger.addAppender(kafkaAppender);
    }
}
