package com.spring.blog.component;

import com.spring.blog.model.Event;
import com.spring.blog.model.Group;
import com.spring.blog.repository.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {

    private final GroupRepository repository;

    public Initializer(GroupRepository groupRepository) {
        this.repository = groupRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG", "Richmond JUG")
                .forEach(name -> repository.save(new Group(name)));

        Group djug = repository.findByName("Denver JUG");
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);

    }
}
