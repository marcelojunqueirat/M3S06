package com.avalialivros.m3s04.model.transport;

import com.avalialivros.m3s04.model.Person;
import com.avalialivros.m3s04.model.enums.NotificationTypeEnum;

public record PersonDTO(String guid, String name, String email, String phone, NotificationTypeEnum notificationType) {

    public PersonDTO(Person person) {
        this(person.getGuid(), person.getName(), person.getEmail(), person.getPhone(), person.getNotificationType());
    }
}
