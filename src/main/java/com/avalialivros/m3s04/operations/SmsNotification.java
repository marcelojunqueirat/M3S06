package com.avalialivros.m3s04.operations;

import com.avalialivros.m3s04.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsNotification implements Notification {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotification.class);

    @Override
    public void send(Person person, String content) {
        LOGGER.info("Notification for {}: {}", person.getPhone(), content);
    }
}
