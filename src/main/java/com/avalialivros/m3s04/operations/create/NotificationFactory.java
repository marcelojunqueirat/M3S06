package com.avalialivros.m3s04.operations.create;

import com.avalialivros.m3s04.exceptions.InvalidNotificationTypeException;
import com.avalialivros.m3s04.model.enums.NotificationTypeEnum;
import com.avalialivros.m3s04.operations.EmailNotification;
import com.avalialivros.m3s04.operations.Notification;
import com.avalialivros.m3s04.operations.SmsNotification;

public class NotificationFactory {
    private NotificationFactory() {
    }

    public static Notification createNotification(NotificationTypeEnum type)
            throws InvalidNotificationTypeException {

        return switch (type) {
            case EMAIL -> new EmailNotification();
            case SMS -> new SmsNotification();
            default -> throw new InvalidNotificationTypeException("Type is not valid: " + type);
        };
    }
}
