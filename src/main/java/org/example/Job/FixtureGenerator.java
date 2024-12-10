package org.example.Job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.example.Model.Fixture;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class FixtureGenerator {
    private final Faker faker = new Faker();
    public List<Fixture> generateFixtures() {
        List<Fixture> fixtures = new ArrayList<>();
        int idCounter = 1;
        for (int i = 0; i < 50; i++) {
            Fixture fixture = new Fixture(
                    idCounter,
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.number().numberBetween(1950, 2000),
                    faker.number().numberBetween(18, 80),
                    faker.options().option("Мужчина", "Женщина")
            );
            fixtures.add(fixture);
            idCounter++;
        }
        return fixtures;
    }
    public void saveFixtures() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("src/main/resources/static/json/fixtures.json");
            objectMapper.writeValue(file, generateFixtures());
            System.out.println("Added");
        }
        catch (IOException e) {
            System.out.println("not Added");
        }
    }
}
