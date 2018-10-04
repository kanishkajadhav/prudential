package Assignment.cucumber.testRunner;

import org.apache.log4j.PropertyConfigurator;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\resources\\Features", 
glue = { "Assignment.cucumber.stepDefinition" }, 
tags = {"@BVT"})

public class TestRunner {
	private TestNGCucumberRunner testNGCucumberRunner;
	private static String scenarioName=null;
	public static WebDriver driver = null;

	@BeforeTest
	public static void BeforeTest() {
		try {
			//ChromeOptions chromeOptions= new ChromeOptions();
			//chromeOptions.setBinary("C:\\Users\\%UserName%\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
			System.setProperty("webdriver.chrome.driver", "lib\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			
			// System.setProperty("webdriver.chrome.driver","C:\\Users\\manish\\Downloads\\chromedriver_win32\\chromedriver.exe");
			 //driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
		} catch (Exception e) {
			Reporter.log("Not able to launch browser. " + e.getMessage(), true);
		}
	}

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		PropertyConfigurator.configure ("log4j.properties");
		System.setProperty("log4j.configurationFile","log4j.properties");
	    testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	
	@Test(groups="cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		
		scenarioName=cucumberFeature.getCucumberFeature().getPath();
		
		System.out.println("**************Executing scenario *********"+scenarioName);
//		System.out.println("**************Executing scenario *********"+cucumberFeature.getCucumberFeature().getPath());
	    testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	   
	}

	@DataProvider
	public Object[][] features() {
		
			   return testNGCucumberRunner.provideFeatures();
	}

	@AfterTest(alwaysRun = true)
	public void afterTest(){
		driver.quit();	
	}


	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
	    testNGCucumberRunner.finish();
	}

	public static String getScenarioName(){
		return scenarioName;
	}
}