package com.avalialivros.m3s04.model;

import com.avalialivros.m3s04.model.enums.NotificationTypeEnum;
import com.avalialivros.m3s04.model.transport.operations.CreatePersonDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity
public class Person implements UserDetails {
    @Id
    @Column(nullable = false, length = 36, unique = true)
    private String guid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationTypeEnum notificationType;

    public Person() {
    }

    public Person(CreatePersonDTO createPersonDTO, String password) {
        this.guid = UUID.randomUUID().toString();
        this.name = createPersonDTO.name();
        this.email = createPersonDTO.email();
        this.phone = createPersonDTO.phone();
        this.password = password;
        this.notificationType = createPersonDTO.notificationType();
    }

    public Person(String name, String email, String phone, String password, NotificationTypeEnum notificationType) {
        this.guid = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.notificationType = notificationType;
    }

    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public NotificationTypeEnum getNotificationType() {
        return notificationType;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
