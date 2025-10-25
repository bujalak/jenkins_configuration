package CucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Features", glue="Step_Definitions", monochrome = true,
plugin = {"pretty",  "html:target/cucumber-reports/html",
        "json:target/cucumber-reports/json/cucumber.json"
        }
		)
public class TestRunner extends AbstractTestNGCucumberTests {
	

}
	
	