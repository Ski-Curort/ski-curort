package com.example.skicurort.weather;

import com.google.gson.annotations.SerializedName;

public class OpenWeatherModel {
    private String name;
    @SerializedName("main")
    private WeatherDetails weatherDetails;
    @SerializedName("wind")
    private WindDetails windDetails;
    @SerializedName("dt")
    private long dateTime;

    OpenWeatherModel() {
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    WeatherDetails getWeatherDetails() {
        return weatherDetails;
    }

    void setWeatherDetails(WeatherDetails weatherDetails) {
        this.weatherDetails = weatherDetails;
    }

    WindDetails getWindDetails() {
        return windDetails;
    }

    void setWindDetails(WindDetails windDetails) {
        this.windDetails = windDetails;
    }

    long getDateTime() {
        return dateTime;
    }

    void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "OpenWeather{" +
                "name='" + name + '\'' +
                ", weatherDetails=" + weatherDetails +
                ", windDetails=" + windDetails +
                ", dateTime=" + dateTime +
                '}';
    }
}

class WeatherDetails {
    private float temp;
    private float pressure;
    private int humidity;

    WeatherDetails(float temp, float pressure, int humidity) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    WeatherDetails() {
    }

    float getTemp() {
        return temp;
    }

    void setTemp(float temp) {
        this.temp = temp;
    }

    float getPressure() {
        return pressure;
    }

    void setPressure(float pressure) {
        this.pressure = pressure;
    }

    int getHumidity() {
        return humidity;
    }

    void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "WeatherDetails{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}

class WindDetails {
    private float speed;
    private int deg;

    WindDetails() {
    }

    WindDetails(float speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }

    float getSpeed() {
        return speed;
    }

    void setSpeed(float speed) {
        this.speed = speed;
    }

    int getDeg() {
        return deg;
    }

    void setDeg(int deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "WindDetails{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}

