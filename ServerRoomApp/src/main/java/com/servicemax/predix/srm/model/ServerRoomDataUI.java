package com.servicemax.predix.srm.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class ServerRoomDataUI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String timeStamp;

	private String serverRoomName;

	private Double termperature;

	private Double humidity;

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getServerRoomName() {
		return serverRoomName;
	}

	public void setServerRoomName(String serverRoomName) {
		this.serverRoomName = serverRoomName;
	}

	public Double getTermperature() {
		return termperature;
	}

	public void setTermperature(Double termperature) {
		this.termperature = termperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
