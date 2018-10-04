package Assignment.cucumber.stepDefinition;

import Assignment.cucumber.Page.Pagefactory;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition extends Pagefactory
{
	public static  Scenario scenario;
//	String accountNumber;
	@Before
	 void readScenario(Scenario scenario)
	 {
		StepDefinition.scenario = scenario;
	 }
	/*@BeforeClass
	public void LaunchBrowser()
	{
		WebDriver driver = new FirefoxDriver();
		driver.get("https://openweathermap.org/");
	}*/
	@Given("^User is on home page$")
	public void user_is_on_home_page() throws Throwable {
		home_Page.VerifyHomePageDisplayed();
	}

	@When("^Verify the basic options displayed on home page$")
	public void verify_the_basic_options_displayed_on_home_page() throws Throwable {
		home_Page.VerifyOptionsOnHomePage();
	}

	@Then("^All the basic fields should be available$")
	public void all_the_basic_fields_should_be_available() throws Throwable {
		home_Page.verifyMapOptions();
	}
	
	@Given("^Verify the Map options displayed in Menu$")
	public void verify_the_Map_options_displayed_in_Menu() throws Throwable {
		home_Page.verifyMapOptions();
	}
	
	@When("^User enters \"(.*?)\" in Search box$")
	public void user_enters_in_Search_box(String cityName) throws Throwable {
	    home_Page.EnterCityName(cityName);
	}

	@Then("^Verify the error message displayed$")
	public void verify_the_error_message_displayed() throws Throwable {
	    home_Page.VerifyErrorMessageDisplayed();
	}
	
	@Then("^Click on \"(.*?)\" link$")
	public void click_on_link(String cityName) throws Throwable {
		home_Page.ClickOnCityNameLink(cityName);
	}

	@Then("^Verify the \"(.*?)\" weather information displayed$")
	public void verify_the_weather_information_displayed(String cityName) throws Throwable {
	    home_Page.VerifyCityWeatherInfo(cityName);
	}
}