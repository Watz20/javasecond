package lesson7.project;

import java.io.IOException;

public interface WeatherModel {
    void getWeather(String selectedCity, Variant period) throws IOException;

    void getSavedWeather();
}


