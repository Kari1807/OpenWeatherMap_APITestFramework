package com.openWeatherMap.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReuseableSpecifications {
    public static RequestSpecBuilder requestSpec;
    public static RequestSpecification requestSpecification;

    public static ResponseSpecBuilder responseSpec;
    public static ResponseSpecification responseSpecification;


    public static RequestSpecification getGenericRequestSpec(){

        requestSpec = new RequestSpecBuilder();
        requestSpec.addQueryParam("appid","39e74f245225b5cad4749d4f3463e15e").addQueryParam("units","metric");
        requestSpecification = requestSpec.build();
        return requestSpecification;

    }

    public static ResponseSpecification getGenericResponseSpec(){
        responseSpec = new ResponseSpecBuilder();
        responseSpec.expectHeader("Content-Type","application/json; charset=utf-8");
        responseSpec.expectHeader("Connection","keep-alive");
        responseSpec.expectStatusCode(200);
        responseSpecification = responseSpec.build();
        return responseSpecification;

    }

}
