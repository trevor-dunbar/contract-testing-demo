package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return a dog"

    request {
        url "/dog/1"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                id: 1,
                name: "Scooby Doo",
                owner: "Shaggy",
                goodDog: true
        )
    }
}