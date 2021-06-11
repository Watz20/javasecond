package lesson7.project;

import lesson7.project.entity.Weather;

import java.sql.*;

public class DataBaseRepository {
    private static final String DB_URL = "jdbc:sqlite:geekbrains.db";
    String insertWeatherRequest = "insert into weather (city_name, weather_text, degrees) values (?, ?, ?)";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //cityName, weatherText, degrees
    public void saveWeather(Weather weather) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertWeatherRequest);
            preparedStatement.setString(1, weather.getCityName());
            preparedStatement.setString(2, weather.getWeatherText());
            preparedStatement.setInt(3, weather.getDegrees());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //(Connection connection = DriverManager.getConnection("jdbc:sqlite:geekbrains.db"))
    public void getSavedWeather() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:geekbrains.db");
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery("select * from weather");

            while (resultSet.next()) {
                System.out.print(resultSet.getString("city_name") + ",");
                System.out.print(resultSet.getString("weather_text") + ",");
                System.out.print(resultSet.getInt("degrees"));
                System.out.println();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


