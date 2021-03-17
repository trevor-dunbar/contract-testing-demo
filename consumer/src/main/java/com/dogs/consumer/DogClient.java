package com.dogs.consumer;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DogClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public Dog[] getDogsFromProvider() {
        return restTemplate.getForEntity("http://localhost:8080/dog", Dog[].class).getBody();
    }

    public Dog getDogByIdFromProvider(int id) {
        return restTemplate.getForEntity("http://localhost:8080/dog/" + id, Dog.class).getBody();
    }

    public Dog postDogToProvider(Dog dog){
        HttpEntity<Dog> request = new HttpEntity<>(dog);
        return restTemplate.postForEntity("http://localhost:8080/dog", request, Dog.class).getBody();
    }
}
