package runner;//package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"applicaionKeyword","stepdefinitions"},
        plugin = {
                "pretty:target/cucumber-htmlreport.html",
                "json:target/report.json",
                "junit:target/report.xml",
                "me.jvt.cucumber.report.PrettyReports:target/pretty-cucumber"
        },
        tags = "not @FE and not @HWSIM")
public class CucumberTestRunner {

  @BeforeClass
  public static void startUp() throws Throwable {
      System.out.println("--startUp---");
  }

    @AfterClass
    public static void teardown() throws Throwable {

        System.out.println("--teardown---");

    }
}