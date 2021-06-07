package lesson7.project;

import com.sun.org.apache.xpath.internal.operations.String;

import java.io.IOException;

public interface WeatherModel {
    void getWeather(String selectedCity, Period period) throws IOException;
}

