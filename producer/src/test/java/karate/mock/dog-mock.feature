Feature: dog producer mock

  Background:
    #{id: 1, name: "Scooby Doo", owner: "Shaggy", goodDog: false, puppies: []}, { id: 2, name: "Baby Bud", goodDog: false }
    * def dogs = []

  Scenario: pathMatches('/dog/{id}') && methodIs('get')
    * def response = dogs[pathParams.id - 1]

  Scenario: pathMatches('/dog') && methodIs('post')
    * def dog = request
    * def id = dogs.size() + 1
    * dog.id = id
    * dogs.add(dog)
    * def response = dog


  Scenario: pathMatches('/dogs')
    * def response = dogs