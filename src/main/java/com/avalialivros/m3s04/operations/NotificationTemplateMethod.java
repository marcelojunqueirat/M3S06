package com.avalialivros.m3s04.operations;

import com.avalialivros.m3s04.exceptions.InvalidNotificationTypeException;
import com.avalialivros.m3s04.model.Person;
import com.avalialivros.m3s04.operations.create.NotificationFactory;

public abstract class NotificationTemplateMethod {

    protected void notify(Person person, String content) throws InvalidNotificationTypeException {
        NotificationFactory
                .createNotification(person.getNotificationType())
                .send(person, content);
    }
}
