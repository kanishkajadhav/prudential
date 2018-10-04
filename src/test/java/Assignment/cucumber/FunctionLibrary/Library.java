package Assignment.cucumber.FunctionLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;

import Assignment.cucumber.testRunner.TestRunner;

public class Library {
	WebDriver driver = TestRunner.driver;
	
	public void waitForPageLoad(long wait) throws InterruptedException
	{
		Thread.sleep(wait);
	}

	public boolean IsElementPresent(WebElement element) {
		boolean result = false;
		try {
			result = element.isDisplayed();
		} catch (Exception e) {
			Reporter.log("Element is Not displayed: " + e.getMessage(), true);
		}
		return result;
	}
	
	public void switchToIframe(WebElement element)
	{
		try {
			driver.switchTo().frame(element);
		} catch (Exception e) {
			Reporter.log("Not able to switch to iFrame "+e.getMessage(),true);
		}
	}
	
	public List<String> GetTextFromList(List<WebElement> elementList)
	{
		List<String> strList = new ArrayList<String>();
		try {
			String str = "";
			for(WebElement element: elementList)
			{
				str = element.getText();
				strList.add(str);
			}
		} catch (Exception e) {
			Reporter.log("Not able to fetch text from WebElement list "+e.getMessage(),true);
		}
		return strList;
	}

	public void Click(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			Reporter.log("Not able to click on element: " + e.getMessage(), true);
		}
	}

	public void EnterText(WebElement element, String text) {
		try {
			element.click();
			element.sendKeys(text);
		} catch (Exception e) {
			Reporter.log("Not able to enter text in textbox: " + e.getMessage(), true);
		}
	}

	@SuppressWarnings("deprecation")
	public void WaitFor(WebElement element) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			wait.withTimeout(5, TimeUnit.SECONDS);
			wait.pollingEvery(250, TimeUnit.MICROSECONDS);
			wait.ignoring(NoSuchElementException.class);

			//wait.wait(1);
			// Thread.sleep(Constants.WAIT_TIME);
		} catch (Exception e) {
			Reporter.log("Not able to Wait --- " + e.getMessage(), true);
		}
	}
	
	public String GetText(WebElement element)
	{
		String text = null;
		try {
			text = element.getText();
		} catch (Exception e) {
			Reporter.log("Not able to fetch the text. "+e.getMessage(),true);
		}
		return text;
	}
}