package com.tistory.itchipmunk.jooq_multiset;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class JooqMultisetApplication implements CommandLineRunner {

    private final DockerImageRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(JooqMultisetApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.getDockerImages().stream()
                .map(DockerImageModel::toString)
                .forEach(log::info);
    }

}
