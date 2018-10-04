package Assignment.cucumber.Page;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import Assignment.cucumber.FunctionLibrary.Library;
import Assignment.cucumber.testRunner.TestRunner;

public class HomePage {
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/div/div/div/div[1]/a/img")
	private WebElement img_OpenWeatherMap;

	@FindBy(how = How.LINK_TEXT, using = "Weather")
	private WebElement lnk_Weather;

	@FindBy(how = How.XPATH, using = "//li[@class='nav__item dropdown']/a")
	private WebElement lnk_Maps;

	@FindBy(how = How.XPATH, using = "//li[4]/ul/li/a")
	private List<WebElement> lnk_MapList;

	@FindBy(how = How.XPATH, using = "//form[@id='searchform']/div/input[@id='q']")
	private WebElement txtbx_Search;

	@FindBy(how = How.XPATH, using = "//form[@id='searchform']/button")
	private WebElement btn_Search;

	@FindBy(how = How.XPATH, using = "//form[@id='searchform']/span/button")
	private WebElement lnk_CurrentSearch;

	@FindBy(how = How.XPATH, using = "//h2[text()='Current weather and forecasts in your city']")
	private WebElement txt_SearchHeading;

	@FindBy(how = How.NAME, using = "google_osd_static_frame")
	private WebElement iFrame_HomePage1;

	@FindBy(how = How.ID, using = "google_esf")
	private WebElement iFrame_HomePage2;

	@FindBy(how = How.XPATH, using = "//div[@id='forecast_list_ul']/div[@role='alert'][contains(text(),'Not found')]")
	private WebElement txt_NotFoundError;

	@FindBy(how = How.XPATH, using = "//tbody/tr/td[2]/b/a[contains(text(),' London, GB')]")
	private WebElement lnk_CityName;

	@FindBy(how = How.XPATH, using = "//div[@id='weather-widget']/h2")
	private WebElement txt_WeatherInCity;

	@FindBy(how = How.XPATH, using = "//div[@id='weather-widget']/h3")
	private WebElement txt_Temprature;

	@FindBy(how = How.XPATH, using = "//div[@class='weather-forecast-hourly-graphic']/h2")
	private WebElement txt_WeatherForecast;

	@FindBy(how = How.XPATH, using = "//div[@class='weather-forecast-hourly-graphic']/div")
	private WebElement img_WeatherForecastGraph;

	Library lib = new Library();
	HomePage hp;

	WebDriver driver = TestRunner.driver;
	boolean result = true;

	public HomePage() {
		// initElements();
	}

	public void initElements() {
		hp = PageFactory.initElements(driver, HomePage.class);
	}

	public void VerifyHomePageDisplayed() {
		try {
			driver.get("https://openweathermap.org/");
			String actualHomePageTitle = driver.getTitle();
			if (actualHomePageTitle.equals("Сurrent weather and forecast - OpenWeatherMap")) {
				Reporter.log("User is on Home Page!", true);
			} else {
				result = false;
				Reporter.log("User is NOT on Home Page!", true);
			}
			Assert.assertEquals(result, true);
		} catch (Exception e) {
			result = false;
			Reporter.log("Not able to navigate to Home Page" + e.getMessage(), true);
		}
		Assert.assertEquals(result, true);
	}

	public void verifyMapOptions() {
		try {
			Reporter.log("Verifying Map available options...");
			lib.Click(hp.lnk_Maps);
			String[] mapTypes = { "Weather maps", "Current satellite maps", "Beautiful places" };
			// String mapName = "";
			List<String> mapList = lib.GetTextFromList(hp.lnk_MapList);

			int i = 0;
			for (String map : mapList) {
				if (map.trim().equals(mapTypes[i])) {
					Reporter.log(map + " is displayed correctly!", true);
				} else {
					Reporter.log(map + " is NOT displayed...", true);
					result = false;
				}
				i++;
			}
		} catch (Exception e) {
			result = false;
			Reporter.log("Not able to verify Map option display. " + e.getMessage(), true);
		}
		Assert.assertEquals(result, true);
	}

	public void VerifyOptionsOnHomePage() {
		initElements();
		try {
			HashMap<String, WebElement> elementList = new HashMap<String, WebElement>();
			String[] contentList = { "Logo", "Weather Menu Option", "Search textBox", "Search button",
					"Current Location link" };
			elementList.put("Logo", hp.img_OpenWeatherMap);
			elementList.put("Weather Menu Option", hp.lnk_Weather);
			elementList.put("Search textBox", hp.txtbx_Search);
			elementList.put("Search button", hp.btn_Search);
			elementList.put("Current Location link", hp.lnk_CurrentSearch);

			boolean isElementDisplayed = false;
			WebElement element = null;
			for (int i = 0; i < elementList.size(); i++) {
				element = elementList.get(contentList[i]);
				lib.WaitFor(element);
				isElementDisplayed = lib.IsElementPresent(element);
				if (isElementDisplayed) {
					Reporter.log(contentList[i] + " is displayed on home page", true);
				} else {
					result = false;
					Reporter.log(contentList[i] + " is NOT displayed on home page", true);
				}
			}
			Assert.assertEquals(result, true);
		} catch (Exception e) {
			Reporter.log("Not able to verify display on 'Open Weather Map' on home page: " + e.getMessage(), true);
		}
		Assert.assertEquals(result, true);
	}

	public void EnterCityName(String cityName) {
		try {
			initElements();
			lib.WaitFor(hp.txtbx_Search);
			Reporter.log("Enter city name in Search box.", true);
			lib.EnterText(hp.txtbx_Search, cityName);
			lib.Click(hp.btn_Search);
		} catch (Exception e) {
			Reporter.log("Not able to enter city name in search box " + e.getMessage(), true);
		}
	}

	public void VerifyErrorMessageDisplayed() {
		String errorText = null;
		try {
			initElements();

			lib.WaitFor(hp.txt_NotFoundError);
			errorText = lib.GetText(hp.txt_NotFoundError);
			System.out.println(errorText);

			if (errorText.trim().contains("Not found")) {
				Reporter.log("Error message displayed correctly for invalid city!", true);
			} else {
				result = false;
				Reporter.log("Error message NOT displayed correctly for invalid city!", true);
			}
		} catch (Exception e) {
			result = false;
			Reporter.log("Not able to verify error message text for invalid city. " + e.getMessage(), true);
		}
		Assert.assertEquals(result, true);
	}

	public void ClickOnCityNameLink(String cityName) {
		try {
			initElements();
			// Click on 'City name' link displayed in search result.
			lib.Click(hp.lnk_CityName);
		} catch (Exception e) {
			Reporter.log("Not able to click on City Name link." + e.getMessage(), true);
		}
	}

	public void VerifyCityWeatherInfo(String cityName) {
		try {
			initElements();
			lib.waitForPageLoad(5000);
			lib.WaitFor(hp.txt_WeatherInCity);
			boolean isWeatherGraphDisplayed = lib.IsElementPresent(hp.img_WeatherForecastGraph);
			boolean cityInfo = lib.GetText(hp.txt_WeatherInCity).trim().contains(cityName)
					&& lib.GetText(hp.txt_WeatherForecast).trim().contains(cityName);
			result = cityInfo && isWeatherGraphDisplayed;
			if (result) {
				String[] tempTxt = lib.GetText(hp.txt_Temprature).split(" ");
				String temp = tempTxt[0];
				Reporter.log("Temprature of " + cityName + " is: " + temp + " " + tempTxt[1], true);
				Reporter.log("Weather forcast for " + cityName + " is displayed!", true);
			} else {
				result = false;
				Reporter.log("Weather forcast for " + cityName + " is NOT displayed!", true);
			}
		} catch (Exception e) {
			result = false;
			Reporter.log("Not able to verify City weather information. " + e.getMessage(), true);
		}
		Assert.assertEquals(result, true);
	}
}
