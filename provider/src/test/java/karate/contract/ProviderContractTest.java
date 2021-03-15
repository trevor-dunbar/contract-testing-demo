package karate.contract;

import com.dogs.ProviderApplication;
import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@KarateOptions(features = "classpath:karate/contract/dog-contract.feature")
@RunWith(Karate.class)
public class ProviderContractTest {

    private static ConfigurableApplicationContext context;

    @BeforeClass
    public static void beforeClass(){
         context = SpringApplication.run(ProviderApplication.class);
    }

    @AfterClass
    public static void afterClass(){
        SpringApplication.exit(context, () -> 0);
    }

}
