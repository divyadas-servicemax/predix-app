package com.servicemax.predix.srm.model;

/*
 {"id": 2,"data": 
 {"name": "Output vs. Capacity 3","values": 
 [[1, 2],[2, 1], [3, 4], [4, 3.9],[5, 4.5],[6, 4.2], [7, 1.5],[8, 6.9]],
 "max": 50,"min": 0,"threshold": 8.9 }
 }

 */
public class LineChartData {

	int id;
	Data data;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}

	
}


