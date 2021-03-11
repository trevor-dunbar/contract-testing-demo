package com.dogs.consumer;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerExtension;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.ArrayUtils.toArray;

@SpringBootTest
class SpringMockContractTest {


    @RegisterExtension
    public StubRunnerExtension stubRunner = new StubRunnerExtension()
            .downloadStub("com.dogs", "producer", "0.0.1-SNAPSHOT", "stubs")
            .withPort(8080)
            .stubsMode(StubRunnerProperties.StubsMode.LOCAL); //local m2 - also valid: remote and classpath


    @Test
    public void getDogFromProducer_contractTest() {
        // given:
        RestTemplate restTemplate = new RestTemplate();

		// when:
        ResponseEntity<Dog> dogResponseEntity = restTemplate.getForEntity("http://localhost:8080/dog/1", Dog.class);

        // then:
		BDDAssertions.then(dogResponseEntity.getStatusCodeValue()).isEqualTo(200);
        BDDAssertions.then(dogResponseEntity.getBody())
                .isEqualTo(Dog.builder().id(1).name("Scooby Doo").owner("Shaggy").goodDog(true).build());
    }

    @Test
    public void getDogFromProducerDogClient_contractTest() {
        // given:
        DogClient client = new DogClient();

        // when:
        Dog dogResponse = client.getDogByIdFromProducer(1);

        // then:
//        BDDAssertions.then(dogResponseEntity.getStatusCodeValue()).isEqualTo(200);
        BDDAssertions.then(dogResponse)
                .isEqualTo(Dog.builder().id(1).name("Scooby Doo").owner("Shaggy").goodDog(true).build());
    }


    @Test
    public void getDogsFromProducer_contractTest(){
        //given
        RestTemplate restTemplate = new RestTemplate();

        //when
        ResponseEntity<Dog[]> dogResponseEntity = restTemplate.getForEntity("http://localhost:8080/dogs", Dog[].class);

        //then
        BDDAssertions.then(dogResponseEntity.getStatusCodeValue()).isEqualTo(200);
        BDDAssertions.then(dogResponseEntity.getBody())
                .isEqualTo(toArray(Dog.builder().id(1).name("Scooby Doo").owner("Shaggy").goodDog(true).build()));
    }

    @Test
    public void postDogToProducer_contractTest(){
        //given
        RestTemplate restTemplate = new RestTemplate();
        Dog dog = Dog.builder()
                .name("new dog")
                .owner("same owner")
                .goodDog(true)
                .puppies(asList(Dog.builder().name("puppy").build()))
                .build();
        HttpEntity<Dog> request = new HttpEntity<>(dog);

        //when
        ResponseEntity<Dog> dogResponseEntity = restTemplate.postForEntity("http://localhost:8080/dog", request, Dog.class);

        //then
        BDDAssertions.then(dogResponseEntity.getStatusCodeValue()).isEqualTo(200);
        dog.setId(2);
        BDDAssertions.then(dogResponseEntity.getBody())
                .isEqualTo(dog);
    }

}
