package com.servicemax.predix.srm.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServerRoom implements Serializable {

	private static final long serialVersionUID = 1500516986755256732L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private long timeStamp;

	private String serverRoomName;

	private Double termperature;

	private Double humidity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
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

	@Override
	public String toString() {
		return "ServerRoom [id=" + id + ", timeStamp=" + timeStamp + ", serverRoomName=" + serverRoomName
				+ ", termperature=" + termperature + ", humidity=" + humidity + "]";
	}

}
