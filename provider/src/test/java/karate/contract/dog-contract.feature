Feature: get and post a dog

  Background:
    * url 'http://localhost:8080'

  Scenario: add a dog then get the dog

    # Post a dog
    Given path 'dog'
    And request { name: "new dog", owner: "new owner", goodDog: true, puppies: [] }
    When method Post
    Then status 200
    And match response == { id: "#number", name: "new dog", owner: "new owner", goodDog: true, puppies: "#array" }
    And def id = response.id

    # Get the newly created dog
    Given path 'dog', id
    When method Get
    Then status 200
    And match response == { id: '#(id)', name: "new dog", owner: "new owner", goodDog: true, puppies: []}

    # Get all Dogs
    Given path 'dogs'
    When method GET
    Then status 200
    And match response contains { id: '#(id)', name: "new dog", owner:"new owner", goodDog: true, puppies: []}
    And match each response contains {id: '#number'}
