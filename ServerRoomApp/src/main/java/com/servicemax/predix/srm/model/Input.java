package com.servicemax.predix.srm.model;

public class Input {

	private String name;
	//private String time;

	private String temperature;
	private String humidity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	@Override
	public String toString() {
		return "Input [name=" + name + ", time=" + temperature + ", humidity=" + humidity
				+ "]";
	}

}
