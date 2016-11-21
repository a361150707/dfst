package com.ennuova.entity;

import java.math.BigDecimal;

/**
 * 天气情况
 * 
 * @author lin
 */
public class WeatherInformation {
	private BigDecimal lng; //经度
	private BigDecimal lat; //纬度
	private String address; //地址
	private String country; //国家
	private String province; //省份
	private String city; //城市
	private String cityCode; //城市编号
	private String district; //区/县
	private String street; //街道
	private String streetNumber; //街道号
	private String date; //日期（年月日）
	private int weatherEnum; //天气情况
	private String weather; //天气情况
	private String temperatureMax; //最高温度
	private String temperatureMin; //最低温度
	private String humidityMax; //最大湿度
	private String humidityMin; //最小湿度
	private String precipitationMax; //最大降雨量
	private String precipitationMin; //最小降雨量
	private String windDirectionStart; //开始风向
	private String windDirectionEnd; //最终风向
	private String windMax; //最大风力
	private String windMin; //最小风力
	private String aqi; //aqi
	private String pm25; //pm2.5
	private String spfName; //防晒指数名称
	private String spf; //防晒指数
	private String spfDetails; //防晒指数建议
	private String dressingName; //穿衣指数名称
	private String dressing; //穿衣指数
	private String dressingDetails; //穿衣指数建议
	private String exerciseName; //运动指数名称
	private String exercise; //运动指数
	private String exerciseDetails; //运动指数建议
	private String washingName;	//洗车指数名称
	private String washing;	//洗车指数
	private String washingDetails; //洗车指数建议
	private String dryingName;	//晾晒指数名称
	private String drying;	//晾晒指数
	private String dryingDetails; //晾晒指数建议
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getWeatherEnum() {
		return weatherEnum;
	}

	public void setWeatherEnum(int weatherEnum) {
		this.weatherEnum = weatherEnum;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTemperatureMax() {
		return temperatureMax;
	}

	public void setTemperatureMax(String temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	public String getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(String temperatureMin) {
		this.temperatureMin = temperatureMin;
	}

	public String getWindDirectionStart() {
		return windDirectionStart;
	}

	public void setWindDirectionStart(String windDirectionStart) {
		this.windDirectionStart = windDirectionStart;
	}

	public String getWindDirectionEnd() {
		return windDirectionEnd;
	}

	public void setWindDirectionEnd(String windDirectionEnd) {
		this.windDirectionEnd = windDirectionEnd;
	}

	public String getHumidityMax() {
		return humidityMax;
	}

	public void setHumidityMax(String humidityMax) {
		this.humidityMax = humidityMax;
	}

	public String getHumidityMin() {
		return humidityMin;
	}

	public void setHumidityMin(String humidityMin) {
		this.humidityMin = humidityMin;
	}

	public String getPrecipitationMax() {
		return precipitationMax;
	}

	public void setPrecipitationMax(String precipitationMax) {
		this.precipitationMax = precipitationMax;
	}

	public String getPrecipitationMin() {
		return precipitationMin;
	}

	public void setPrecipitationMin(String precipitationMin) {
		this.precipitationMin = precipitationMin;
	}

	public String getWindMax() {
		return windMax;
	}

	public void setWindMax(String windMax) {
		this.windMax = windMax;
	}

	public String getWindMin() {
		return windMin;
	}

	public void setWindMin(String windMin) {
		this.windMin = windMin;
	}

	public String getAqi() {
		return aqi;
	}

	public void setAqi(String aqi) {
		this.aqi = aqi;
	}

	public String getPm25() {
		return pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	public String getSpf() {
		return spf;
	}

	public void setSpf(String spf) {
		this.spf = spf;
	}

	public String getSpfName() {
		return spfName;
	}

	public void setSpfName(String spfName) {
		this.spfName = spfName;
	}

	public String getSpfDetails() {
		return spfDetails;
	}

	public void setSpfDetails(String spfDetails) {
		this.spfDetails = spfDetails;
	}

	public String getDressingName() {
		return dressingName;
	}

	public void setDressingName(String dressingName) {
		this.dressingName = dressingName;
	}

	public String getDressing() {
		return dressing;
	}

	public void setDressing(String dressing) {
		this.dressing = dressing;
	}

	public String getDressingDetails() {
		return dressingDetails;
	}

	public void setDressingDetails(String dressingDetails) {
		this.dressingDetails = dressingDetails;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	public String getExerciseDetails() {
		return exerciseDetails;
	}

	public void setExerciseDetails(String exerciseDetails) {
		this.exerciseDetails = exerciseDetails;
	}

	public String getWashingName() {
		return washingName;
	}

	public void setWashingName(String washingName) {
		this.washingName = washingName;
	}

	public String getWashing() {
		return washing;
	}

	public void setWashing(String washing) {
		this.washing = washing;
	}

	public String getWashingDetails() {
		return washingDetails;
	}

	public void setWashingDetails(String washingDetails) {
		this.washingDetails = washingDetails;
	}

	public String getDryingName() {
		return dryingName;
	}

	public void setDryingName(String dryingName) {
		this.dryingName = dryingName;
	}

	public String getDrying() {
		return drying;
	}

	public void setDrying(String drying) {
		this.drying = drying;
	}

	public String getDryingDetails() {
		return dryingDetails;
	}

	public void setDryingDetails(String dryingDetails) {
		this.dryingDetails = dryingDetails;
	}
}