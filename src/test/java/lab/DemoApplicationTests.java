package lab;

import lab.model.Calculation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import lab.controller.CalculationController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoApplicationTests {

    @Autowired
    CalculationController paramsController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void missingParam() {
        String actual = testRestTemplate.getForObject("http://localhost:8080/calculate?weight1=6&speed1=100", String.class);
        String excepted = "{\"message\":\"Required request parameter 'weight2' for method parameter type double is not present\",\"code\":400}";
        assertEquals(excepted, actual);
    }

    @Test
    public void negativeParameters() {
        String actual = testRestTemplate.getForObject("http://localhost:8080/calculate?weight1=6&speed1=100&weight2=9&speed2=-150", String.class);
        String expected = "{\"message\":\"Parameters are negative\",\"code\":500}";
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculating() {
        double speed1 = 100;
        double weight1 = 6;
        double speed2 = 150;
        double weight2 = 9;
        Calculation testParams = new Calculation(130);
        assertEquals(testParams.getSpeed(), paramsController.calculateSpeed(weight1, speed1, weight2, speed2).getSpeed());
    }

}