package com.avalialivros.m3s04.service;

import com.avalialivros.m3s04.exceptions.InvalidNotificationTypeException;
import com.avalialivros.m3s04.exceptions.PersonNotFoundException;
import com.avalialivros.m3s04.model.Person;
import com.avalialivros.m3s04.model.builder.PersonBuilder;
import com.avalialivros.m3s04.model.transport.PersonDTO;
import com.avalialivros.m3s04.model.transport.operations.CreatePersonDTO;
import com.avalialivros.m3s04.operations.NotificationTemplateMethod;
import com.avalialivros.m3s04.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends NotificationTemplateMethod implements UserDetailsService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.personRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na busca por e-mail: " + username));
    }

    @Transactional
    public PersonDTO create(CreatePersonDTO createPersonDTO) {
        LOGGER.info("Iniciando criação de usuário...");
        String passwordEnconded = this.passwordEncoder.encode(createPersonDTO.password());
        Person person = this.buildPerson(createPersonDTO, passwordEnconded);
        this.personRepository.save(person);

        this.sendNotification(person);
        return new PersonDTO(person);
    }

    private void sendNotification(Person person) throws InvalidNotificationTypeException {
        String content = person.getNotificationType() +
                ": Hello! Welcome to the books management platform.";
        super.notify(person, content);
    }

    public Person findByEmail(String email) throws PersonNotFoundException {
        LOGGER.info("Buscando usuário por e-mail...");
        return this.personRepository.findByEmail(email)
                .orElseThrow(() -> new PersonNotFoundException("Usuário não encontrado"));
    }

    private Person buildPerson(CreatePersonDTO createPersonDTO, String password) {
        return PersonBuilder.builder()
                .name(createPersonDTO.name())
                .email(createPersonDTO.email())
                .phone(createPersonDTO.phone())
                .password(password)
                .notificationType(createPersonDTO.notificationType())
                .build();
    }
}
