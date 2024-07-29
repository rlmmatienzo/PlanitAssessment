package runner;

import java.io.*;
import org.junit.AfterClass;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/test/resources/features",
        glue = {"stepDefinitions"},
        tags = "@TestScenario3",
        plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
                "progress",
                "summary"}
        //plugin = {"pretty", "json:target/cucumber.json",  "pretty:target/cucumber-pretty.txt", "html:target/cucumber-html.html", "rerun:target/rerun.txt"}
)
public class TestRunner {
}