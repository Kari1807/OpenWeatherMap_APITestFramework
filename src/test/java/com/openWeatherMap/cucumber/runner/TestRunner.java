package com.openWeatherMap.cucumber.runner;

import com.openWeatherMap.testBase.TestBase;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.aspectj.weaver.ast.Test;
import org.junit.runner.RunWith;

@RunWith(value= CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features", glue = {"com.openWeatherMap.cucumber.glue"}, tags = "@test")
public class TestRunner extends TestBase {
}
