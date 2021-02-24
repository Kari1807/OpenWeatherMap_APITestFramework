package com.openWeatherMap.cucumber.glue;

import com.openWeatherMap.cucumber.stepDefinition.OpenWeatherMapStepDef;
import com.openWeatherMap.utils.ReuseableSpecifications;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class OpenWeatherMapGlue {
    @Steps
    OpenWeatherMapStepDef openWeatherMapStepDef;
    ReuseableSpecifications reuseableSpecifications;
    @Given("^the user wants to know the weather forecast in \"(.*)\"$")
    public void the_user_wants_to_know_the_weather_forecast_in(String cityName) {
       openWeatherMapStepDef.getWeatherForcastByCityName(cityName);
    }

    @When("^the user invokes the openweathermap api$")
    public void the_user_invokes_the_openweathermap_api() {
        openWeatherMapStepDef.fetchWeatherForecast();
    }

    @Then("^the user should see the number of days when temperature is above \"(.*)\" degrees$")
    public void the_user_should_see_the_number_of_days_when_temperature_is_above_degrees(Integer givenTemperature) {
        openWeatherMapStepDef.calculateNumberOfDaysTempAboveTwenty(givenTemperature);
    }

    @Then("^the user should see the number of days when weather is Sunny \"(.*)\"$")
    public void the_user_should_see_the_number_of_days_when_weather_is_sunny(String weatherCondition) {
      openWeatherMapStepDef.calculateNumberOfDaysSunnyWeather(weatherCondition);
      openWeatherMapStepDef.weatherForecastReport();
    }





}
