package CucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Features", glue="Step_Definitions", monochrome = true,
plugin = {"pretty",  "html:test-output/html-report",
        "json:test-output/json-report/cucumber.json"
        }
		)
public class TestRunner extends AbstractTestNGCucumberTests {
	

}
	
	