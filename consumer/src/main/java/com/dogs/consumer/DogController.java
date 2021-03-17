package com.dogs.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DogController {

    private final DogClient dogClient;

    @GetMapping("/dog")
    public List<Dog> getDogs() {
        return Arrays.asList(dogClient.getDogsFromProvider());
    }

    @GetMapping("/dog/{id}")
    public Dog getDogById(@PathVariable int id) {
        return dogClient.getDogByIdFromProvider(id);
    }

    @PostMapping("/dog")
    public Dog addDog(Dog dog) {
        return dogClient.postDogToProvider(dog);
    }
}
