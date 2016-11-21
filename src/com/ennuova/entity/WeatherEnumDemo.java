package com.ennuova.entity;

public class WeatherEnumDemo {
	public enum WeatherEnum {				
		qingtian,
		duoyun,
		yintian,
		xiaxue,
		xiayu
	};
	
	WeatherEnum weatherEnum;
	
	public WeatherEnumDemo(WeatherEnum weatherEnum) {
        this.weatherEnum = weatherEnum;
    }

	public int getWeatherEnum() {
		switch (weatherEnum) {
		case qingtian:
			return 1;
		case duoyun:
			return 2;
		case yintian:
			return 3;
		case xiaxue:
			return 4;
		default:
			return 5;
		}
    }
}