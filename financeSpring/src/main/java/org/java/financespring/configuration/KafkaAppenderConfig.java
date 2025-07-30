package org.java.financespring.configuration;

import ch.qos.logback.classic.LoggerContext;
import com.github.danielwegener.logback.kafka.KafkaAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaAppenderConfig {

    @Bean
    public KafkaAppender kafkaAppender() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        KafkaAppender appender = new KafkaAppender();
        appender.setName("KAFKA");
        appender.setContext(context);
        appender.setTopic("logs-topic");

        appender.addProducerConfigValue("bootstrap.servers", "localhost:9092");
        appender.setEncoder(new LogstashEncoder());

        return appender;
    }
}
