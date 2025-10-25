package CucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Features", glue="Step_Definitions", monochrome = true,
plugin = {"pretty", "html:target/cucumber.html","json:target/cucumber2.json"}, tags="@smoke"
		)
public class TestRunner extends AbstractTestNGCucumberTests {
	

}
	
	