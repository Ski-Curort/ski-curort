package com.example.skicurort.weather;


public class FetchWeatherByCity extends BaseFetchWeatherByCityName<OpenWeatherModel> {

    public FetchWeatherByCity(String cityName) {
        super(cityName);
    }

    @Override
    public String getUrl() {
        return ApiConfig.getInstance().getByCityNameQuery(cityName);
    }

    @Override
    public Class<OpenWeatherModel> getClasz() {
        return OpenWeatherModel.class;
    }


}
