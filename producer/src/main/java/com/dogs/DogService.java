package com.dogs;

import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.singletonList;

@Service
public class DogService {

    private Map<Integer, Dog> dogDB;

    public DogService(){
        dogDB = new HashMap<>();

        Dog babyBud = Dog.builder().id(2).name("Baby Scooby").goodDog(false).build();
        dogDB.put(babyBud.getId(), babyBud);

        Dog scoobyDoo = Dog.builder().id(1).name("Scooby Doo").goodDog(true).puppies(singletonList(babyBud)).build();
        dogDB.put(scoobyDoo.getId(), scoobyDoo);
    }


    public List<Dog> getDogs() {
        return new ArrayList<>(dogDB.values());
    }

    public Dog getDogById(int id) {
        return dogDB.get(id);
    }

    public Dog addDog(Dog dog){
        dogDB.put(dogDB.size() + 1, dog);
        dog.setId(dogDB.size());
        return dog;
    }
}
