package com.avalialivros.m3s04.operations;

import com.avalialivros.m3s04.model.Person;

public interface Notification {

    void send(Person person, String content);
}
