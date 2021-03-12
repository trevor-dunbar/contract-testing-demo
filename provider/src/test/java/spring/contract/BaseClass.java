package spring.contract;

import com.dogs.DogController;
import com.dogs.ProviderApplication;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = ProviderApplication.class)
abstract class BaseClass {

    @Autowired
    DogController dogController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(dogController);
    }
}
