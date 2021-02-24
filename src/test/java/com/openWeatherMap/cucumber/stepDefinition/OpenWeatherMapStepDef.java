package com.openWeatherMap.cucumber.stepDefinition;

import com.openWeatherMap.utils.ReuseableSpecifications;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OpenWeatherMapStepDef extends ReuseableSpecifications {
    RequestSpecification requestSpecification;
    String response;
    int numberOfDaysWithSunnyWeather;
    int numberOfDaysTempAbove20;
    @Step("Getting the 5 days weather forcast for the city ")
    public void getWeatherForcastByCityName(String cityName){
               requestSpecification= SerenityRest.rest()
                .given().queryParam("q",cityName)
                .spec(getGenericRequestSpec());
    }

    @Step("Calling OpenWeatherMap api to fetch the forecast details")
    public String fetchWeatherForecast(){
        response =requestSpecification.when().get("data/2.5/forecast")
                .then().spec(getGenericResponseSpec()).extract().response().asString();
        return response;
    }

    @Step("Calculate number of days where temperature is above 20 degree")
    public void calculateNumberOfDaysTempAboveTwenty(int givenTemperature) {
        JsonPath js = new JsonPath(response);
        int numberOfRecords = js.getInt("cnt");
        List<String> dateArrayList = new ArrayList<String>();
        for (int i = 0; i < numberOfRecords; i++) {
                Float maxTemperature = js.getFloat("list[" + i + "].main.temp_max");
            if (maxTemperature > givenTemperature) {
                String dateTime = js.get("list[" + i + "].dt_txt");
                String date = dateTime.substring(0, 10);
                dateArrayList.add(date);
                Set<String> set = new HashSet<>(dateArrayList);
                dateArrayList.clear();
                dateArrayList.addAll(set);
            }
        }
         numberOfDaysTempAbove20 = dateArrayList.size();
    }

    @Step("Calculate number of days where weather is Sunny")
    public void calculateNumberOfDaysSunnyWeather(String expectedWeatherCondition) {
        JsonPath js = new JsonPath(response);
        int numberOfRecords = js.getInt("cnt");
        List<String> dateArrayList = new ArrayList<String>();
        for (int i = 0; i < numberOfRecords; i++) {
            String weatherCondition = js.getString("list[" + i + "].weather[0].main");
                if(weatherCondition.equalsIgnoreCase(expectedWeatherCondition)) {
                    String dateTime = js.get("list[" + i + "].dt_txt");
                    String date = dateTime.substring(0, 10);
                    dateArrayList.add(date);
                    Set<String> set = new HashSet<>(dateArrayList);
                    dateArrayList.clear();
                    dateArrayList.addAll(set);
            }
        }
         numberOfDaysWithSunnyWeather = dateArrayList.size();
    }

    @Step("Weather Forecast Report")
    public void weatherForecastReport() {
        String abc=String.valueOf(numberOfDaysWithSunnyWeather);
        String xyz = String.valueOf(numberOfDaysTempAbove20);
        Serenity.recordReportData().withTitle("Weather Forecast Report for next 5 days").andContents("Number of Days Predicted to have Temperature above 20 degrees:"+" "+numberOfDaysTempAbove20 +"\n" +
                "Number of Days Predicted to be Sunny:" +" "+numberOfDaysWithSunnyWeather);

    }
}

