Feature: Display 5 days weather forecast based on the City.

   @test
   Scenario Outline: To calculate the number of days in Sydney where the temperature will be above 20 degrees and weather will be sunny in next 5 days inclusive of current date
    Given the user wants to know the weather forecast in "<cityName>"
    When the user invokes the openweathermap api
    Then the user should see the number of days when temperature is above "<givenTemperature>" degrees
    And the user should see the number of days when weather is Sunny "<weatherCondition>"

     Examples:
     |cityName|givenTemperature|weatherCondition|
     |Sydney  |20              |Clear           |

















