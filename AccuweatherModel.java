package lesson7.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson7.project.entity.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/currentconditions/v1/
    //http://dataservice.accuweather.com/forecasts/v1/daily/5day/
    private static final String PROTOCOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String CURRENT_CONDITIONS = "currentconditions";
    private static final String FORECASTS = "forecasts";
    private static final String DAILY = "daily";
    private static final String FIVE_DAYS = "5day";
    private static final String VERSION = "v1";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final String API_KEY = "1jXyTFpM2AGKYdw2cK3UGuAGxU2vqlbO";
    static OkHttpClient okHttpClient = new OkHttpClient();
    static ObjectMapper objectMapper = new ObjectMapper();

    public void getWeather(String selectedCity, Period period) throws IOException {
        if (period == Period.NOW) {
            HttpUrl httpUrl = new HttpUrl.Builder()
                    .scheme(PROTOCOL)
                    .host(BASE_HOST)
                    .addPathSegment(CURRENT_CONDITIONS)
                    .addPathSegment(VERSION)
                    .addPathSegment(getCityKey(selectedCity))
                    .addQueryParameter("apikey", API_KEY)
                    .build();

            Request request = new Request.Builder()
                    .url(httpUrl)
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            String responseString = response.body().string();
            String weatherText = objectMapper.readTree(responseString).get(0).at("/WeatherText").asText();
            Integer degrees = objectMapper.readTree(responseString).get(0).at("/Temperature/Metric/Value").asInt();
            Weather weather = new Weather(selectedCity, weatherText, degrees);
            System.out.println(weather);
            //System.out.println(response.body().string());
        }
        //http://dataservice.accuweather.com/forecasts/v1/daily/5day/
        if (period == Period.FIVE_DAYS) {
            HttpUrl httpUrl = new HttpUrl.Builder()
                    .scheme(PROTOCOL)
                    .host(BASE_HOST)
                    .addPathSegment(FORECASTS)
                    .addPathSegment(VERSION)
                    .addPathSegment(DAILY)
                    .addPathSegment(FIVE_DAYS)
                    .addPathSegment(getCityKey(selectedCity))
                    .addQueryParameter("apikey", API_KEY)
                    .build();

            Request request = new Request.Builder()
                    .url(httpUrl)
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());
        }
    }

    public String getCityKey(String city) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", city)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseBody = response.body().string();

        String cityKey = objectMapper.readTree(responseBody).get(0).at("/Key").asText();
        //System.out.println(cityKey);
        return cityKey;
    }
}
