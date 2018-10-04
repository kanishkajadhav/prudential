Feature: This feature file contains basic scenarios.

@BVT
Scenario: Verify the basic options available on Home page
When User is on home page
Then Verify the basic options displayed on home page
And Verify the Map options displayed in Menu
# Page Title, Menu Options, Logo, Home page message, Search box, Search button, Current Location, "Current weather and forecasts in your city", Main, Daily, Hourly, Chart, Map
#Sentinel-2 via Openweather API, APIs for Agriculture on agromonitoring.com, Weather maps, Current weather, Weather layers, Daily satellite map
# Google Weather-Based Campaign Management with OpenWeatherMap API,  2500+ OpenWeatherMap weather API repositories on GitHub, Weather APIs for developers, Connect your weather station to OpenWeatherMap
#Then All the basic fields should be available

@BVT
Scenario Outline: Verify the functionality with invalid city name
Given User is on home page
When User enters "<City_Name>" in Search box
Then Verify the error message displayed

Examples:
|City_Name|
|Abcd|

@BVT
Scenario Outline: Verify the functionality with invalid city name
Given User is on home page
When User enters "<City_Name>" in Search box
Then Click on "<City_Name>" link
And Verify the "<City_Name>" weather information displayed 

Examples:
|City_Name|
|London, GB|