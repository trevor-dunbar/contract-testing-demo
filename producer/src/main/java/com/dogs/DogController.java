package com.dogs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DogController {

    private final DogService dogService;

    @GetMapping("/dogs")
    public List<Dog> getDogs(){
        log.info("Getting Dogs");
        return dogService.getDogs();
    }

    @GetMapping("/dog/{id}")
    public Dog getDogById(@PathVariable int id){
        log.info("Getting Dog: " + id);
        return dogService.getDogById(id);
    }

    @PostMapping("/dog")
    public Dog addDog(@RequestBody Dog dog){
        log.info("adding dog");
        return dogService.addDog(dog);
    }
}
