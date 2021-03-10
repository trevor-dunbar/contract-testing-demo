package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return a list dogs"

    request {
        url "/dogs"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(file("getDogsResponse.json"))
    }
}



