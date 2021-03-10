package com.dogs.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    private int id;
    private String name;
    private String owner;
    private boolean goodDog;
    private List<Dog> puppies;

}
