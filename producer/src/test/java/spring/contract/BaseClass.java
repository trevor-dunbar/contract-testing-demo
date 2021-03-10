package spring.contract;

import com.dogs.Dog;
import com.dogs.DogController;
import com.dogs.DogService;
import com.dogs.ProducerApplication;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest(classes = ProducerApplication.class)
abstract class BaseClass {

    @Autowired
    DogController dogController;

    @MockBean
    DogService dogService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(dogController);

        Dog dog =  Dog.builder()
                .id(1)
                .name("Scooby Doo")
                .owner("Shaggy")
                .goodDog(false)
                .build();

        Dog newDog = Dog.builder()
                .id(2)
                .name("new dog")
                .owner("same owner")
                .goodDog(true)
                .puppies(asList(Dog.builder().name("puppy").build()))
                .build();

        Mockito.when(dogService.getDogById(1))
                .thenReturn(dog);

        Mockito.when(dogService.getDogs()).thenReturn(singletonList(dog));

        Mockito.when(dogService.addDog(any(Dog.class))).thenReturn(newDog);
    }
}
