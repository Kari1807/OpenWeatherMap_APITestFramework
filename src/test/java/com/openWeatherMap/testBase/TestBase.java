package com.openWeatherMap.testBase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {
    @BeforeClass
    public static void init(){
        RestAssured.baseURI="http://api.openweathermap.org";
    }
}
