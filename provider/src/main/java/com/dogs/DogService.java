package com.dogs;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

@Service
public class DogService {

    private Map<Integer, Dog> dogDB;

    public DogService() {
        dogDB = new HashMap<>();

        Dog scoobyDoo = Dog.builder().id(1).name("Scooby Doo").owner("Shaggy").goodDog(true).puppies(emptyList()).build();
        dogDB.put(scoobyDoo.getId(), scoobyDoo);
    }


    public List<Dog> getDogs() {
        return new ArrayList<>(dogDB.values());
    }

    public Dog getDogById(int id) {
        return dogDB.get(id);
    }

    public Dog addDog(Dog dog) {
        dogDB.put(dogDB.size() + 1, dog);
        dog.setId(dogDB.size());
        return dog;
    }
}
