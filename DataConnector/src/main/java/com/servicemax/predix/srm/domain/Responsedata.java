package com.servicemax.predix.srm.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="responsedata")
public class Responsedata implements Serializable {

	private static final long serialVersionUID = 1500516986755256732L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private int sum;

	private String timeStamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Responsedata [id=" + id + ", sum=" + sum + ", timeStamp=" + timeStamp + "]";
	}

	
}
