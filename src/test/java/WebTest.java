import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WebTest extends TestBase {

    @Test
    void checkTittleTest(){
        driver.get("https://gitlab.com");
        assertThat(driver.getTitle()).containsIgnoringCase("The most-comprehensive " +
                "AI-powered DevSecOps platform");
    }
}
