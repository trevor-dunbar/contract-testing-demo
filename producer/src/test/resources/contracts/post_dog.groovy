package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "posting a dog should return a new dog"

    request {
        url "/dog"
        method POST()
        headers {
            contentType applicationJson()
        }
        body(file("postDogRequest.json"))
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(file("postDogResponse.json"))
    }
}