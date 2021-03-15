package com.dogs.consumer;


import com.intuit.karate.netty.FeatureServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class KarateMockContractTest {

    private static FeatureServer server;

    @BeforeClass
    public static void beforeClass() {
        File file = new File("../provider/src/test/java/karate/mock/dog-mock.feature");
        server = FeatureServer.start(file, 8080, false, null);
    }

    @Test
    public void getDogs_postDog_getDogs_getDog() {
        DogClient dogClient = new DogClient();

        //Get Dogs starts with no dogs
        Dog[] getDogsResponse = dogClient.getDogsFromProvider();
        assertEquals(getDogsResponse.length, 0);

        //Add a dog
        Dog dog = Dog.builder()
                .name("New dog").owner("different owner").goodDog(false).puppies(emptyList())
                .build();
        Dog postDogResponse = dogClient.postDogToProvider(dog);
        dog.setId(1);
        assertEquals(dog, postDogResponse);

        //get new dog
        Dog getDogResponse = dogClient.getDogByIdFromProvider(1);
        assertEquals(getDogResponse, dog);

        //get all dogs has new dog
        getDogsResponse = dogClient.getDogsFromProvider();
        assertEquals(getDogsResponse[0], dog);
    }

    @AfterClass
    public static void afterClass() {
        server.stop();
    }

}
