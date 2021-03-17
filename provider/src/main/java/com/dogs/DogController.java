package com.dogs;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    @GetMapping("/dog")
    public List<Dog> getDogs() {
        return dogService.getDogs();
    }

    @GetMapping("/dog/{id}")
    public Dog getDogById(@PathVariable int id) {
        return dogService.getDogById(id);
    }

    @PostMapping("/dog")
    public Dog addDog(@RequestBody Dog dog) {
        return dogService.addDog(dog);
    }
}
