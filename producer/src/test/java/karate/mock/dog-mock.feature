Feature: dog producer mock

  Background:
    * def dogs = []

  Scenario: pathMatches('/dog') && methodIs('post')
    * def dog = request
    * def id = dogs.size() + 1
    * dog.id = id
    * dogs.add(dog)
    * def response = { id:'#(id)', name: '#(dog.name)',  owner: '#(dog.owner)', goodDog: '#(dog.goodDog)', puppies: '#(dog.puppies)' }

  Scenario: pathMatches('/dog/{id}') && methodIs('get')
    * def response = dogs[pathParams.id - 1]


  Scenario: pathMatches('/dogs')
    * def response = dogs