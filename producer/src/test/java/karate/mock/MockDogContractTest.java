package karate.mock;

import com.intuit.karate.FileUtils;
import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;
import com.intuit.karate.netty.FeatureServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Karate.class)
@KarateOptions(features = "classpath:karate/contract/dog-contract.feature")
public class MockDogContractTest {
    private static FeatureServer server;

    @BeforeClass
    public static void beforeClass() {
        File file = FileUtils.getFileRelativeTo(MockDogContractTest.class, "dog-mock.feature");
        server = FeatureServer.start(file, 8080, false, null);
    }

    @AfterClass
    public static void afterClass() {
        server.stop();
    }
}
