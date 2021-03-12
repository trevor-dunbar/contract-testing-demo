package com.dogs.consumer;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.*;

@Service
public class DogClient {

    private final String baseUrl = "http://localhost:8080";

    private final RestTemplate restTemplate = new RestTemplate();

    public Dog[] getDogsFromProvider() {
        return restTemplate.getForEntity(baseUrl.concat("/dogs"), Dog[].class).getBody();
    }

    public Dog getDogByIdFromProvider(int id) {
        return restTemplate.getForEntity(baseUrl.concat("/dog/" + id), Dog.class).getBody();
    }

    public Dog postDogToProvider(Dog dog){
        HttpEntity<Dog> request = new HttpEntity<>(dog);
        return restTemplate.postForEntity(baseUrl.concat("/dog"), request, Dog.class).getBody();
    }
}
