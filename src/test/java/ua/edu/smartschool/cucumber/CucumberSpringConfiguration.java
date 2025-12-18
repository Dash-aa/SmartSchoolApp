package ua.edu.smartschool.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import ua.edu.smartschool.SmartSchoolApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = SmartSchoolApplication.class)
public class CucumberSpringConfiguration {
}
