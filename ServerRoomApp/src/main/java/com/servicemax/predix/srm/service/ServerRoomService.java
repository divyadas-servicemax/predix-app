package com.servicemax.predix.srm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicemax.predix.srm.domain.ServerRoom;
import com.servicemax.predix.srm.model.Data;
import com.servicemax.predix.srm.model.Input;
import com.servicemax.predix.srm.model.LineChartData;
import com.servicemax.predix.srm.model.ServerRoomDataUI;
import com.servicemax.predix.srm.util.ApplicationConstants;
import com.servicemax.predix.srm.util.Utility;

@Service
@SuppressWarnings("unchecked")
@Transactional
public class ServerRoomService {

	@PersistenceContext
	private EntityManager em;

	public String insertServerData(Input input) {
		String result = ApplicationConstants.FAILURE;
		System.out.println("*****  trying to insert server room data , input = " + input);
		try {
			// JSONObject(datasetDetails);
			String serverName = input.getName();
			Long time = new Date().getTime();
			Double temperature = Double.parseDouble(input.getTemperature());
			Double humidity = Double.parseDouble(input.getHumidity());

			System.out.println("*******  input data values name,time,temp,humidity : " + serverName + " , " + time
					+ " , " + temperature + " , " + humidity);

			ServerRoom serverRoomData = new ServerRoom();
			serverRoomData.setServerRoomName(serverName);
			serverRoomData.setTimeStamp(time);
			serverRoomData.setTermperature(temperature);
			serverRoomData.setHumidity(humidity);
			em.persist(serverRoomData);
			result = ApplicationConstants.SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String insertServerDataJson(String inputData) {
		String result = ApplicationConstants.FAILURE;
		System.out.println("*****  trying to insert server room data , input = " + inputData);
		try {
			if (!inputData.isEmpty()) {
				JSONObject serverRoomJson = new JSONObject(inputData);

				String serverName = serverRoomJson.getString(ApplicationConstants.SERVER_NAME);
				Long time = new Date().getTime();
				Double temperature = Double.parseDouble(serverRoomJson.getString(ApplicationConstants.TEMPERATURE));
				Double humidity = Double.parseDouble(serverRoomJson.getString(ApplicationConstants.HUMIDITY));

				System.out.println("*******  input data values name,time,temp,humidity : " + serverName + " , " + time
						+ " , " + temperature + " , " + humidity);

				ServerRoom serverRoomData = new ServerRoom();
				serverRoomData.setServerRoomName(serverName);
				serverRoomData.setTimeStamp(time);
				serverRoomData.setTermperature(temperature);
				serverRoomData.setHumidity(humidity);
				em.persist(serverRoomData);
				result = ApplicationConstants.SUCCESS;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional(readOnly = true)
	public String getAllServerRoomData() {
		List<ServerRoomDataUI> serverRoomDataList = new ArrayList<>();
		System.out.println("****  trying to query Server room data ");
		List<ServerRoom> dbList = em.createQuery("FROM ServerRoom ORDER BY timeStamp").getResultList();
		ServerRoomDataUI serverRoomDataUI;
		for (ServerRoom srd : dbList) {
			serverRoomDataUI = new ServerRoomDataUI();
			serverRoomDataUI.setServerRoomName(srd.getServerRoomName());
			serverRoomDataUI.setHumidity(srd.getHumidity());
			serverRoomDataUI.setTermperature(srd.getTermperature());
			serverRoomDataUI.setTimeStamp(Utility.getReadableDateForUI(srd.getTimeStamp()));

			serverRoomDataList.add(serverRoomDataUI);
		}

		return Utility.convertObjectToString(serverRoomDataList);
	}

	public String getAllServerRoomTempData() {

		System.out.println("****  trying to query Server room data ");
		List<ServerRoom> dbList = em.createQuery("FROM ServerRoom ORDER BY timeStamp").getResultList();
		LineChartData lineChartData = new LineChartData();
		Double temperature;
		int counter = 1;

		lineChartData.setId(1);
		Data data = new Data();
		data.setMax(40);
		data.setMin(10);
		data.setThreshold(30);
		data.setName("Temperature");

		String dataPointsValues = "[";
		/*
		 * {"id": 2,"data": {"name": "Output vs. Capacity 3","values": [[1,
		 * 2],[2, 1], [3, 4], [4, 3.9],[5, 4.5],[6, 4.2], [7, 1.5],[8, 6.9]],
		 * "max": 50,"min": 0,"threshold": 8.9 } }
		 * 
		 * 
		 * {"id":1,"data":{"name":"Server room temperature trend","values":
		 * "[[1,29.2],[2,29.2],[3,29.2],[4,19.2],[5,22.2],[6,25.2],[7,26.2],[8,26.2],]",
		 * "max":30,"min":10,"threshold":25}}
		 * 
		 */
		String temp = "";

		for (ServerRoom srd : dbList) {

			temperature = srd.getTermperature();

			temp = "[";

			temp = temp + counter + "," + temperature;

			temp = temp + "]";

			System.out.println("**************** " + temp);
			dataPointsValues = dataPointsValues + temp + ",";

			counter++;
		}
		int length = dataPointsValues.length();
		dataPointsValues = dataPointsValues.substring(0, length - 1);
		dataPointsValues = dataPointsValues + "]";
		//data.setValues(dataPointsValues);
		data.setValues((Object)dataPointsValues);

		System.out.println("********* Line chart data = " + dataPointsValues);
		lineChartData.setData(data);

		String result = Utility.convertObjectToString(lineChartData);
		String r = result;
		
		System.out.println("*************** BEFORE  " + r);
		
		r=r.replace("values\":\"", "values\":");
		
		r = r.replace("\",\"max\"", ",\"max\"");
		
		
		
		
		System.out.println("*************** AFTER  " + r);
		return r;

	}

	public String getAllServerRoomHumidityData() {

		System.out.println("****  trying to query Server room data ");
		List<ServerRoom> dbList = em.createQuery("FROM ServerRoom ORDER BY timeStamp").getResultList();
		LineChartData lineChartData = new LineChartData();
		Double humidity;
		int counter = 1;

		lineChartData.setId(2);
		Data data = new Data();
		data.setMax(70);
		data.setMin(30);
		data.setThreshold(55);
		data.setName("Humidity");

		String dataPointsValues = "[";
		/*
		 * {"id": 2,"data": {"name": "Output vs. Capacity 3","values": [[1,
		 * 2],[2, 1], [3, 4], [4, 3.9],[5, 4.5],[6, 4.2], [7, 1.5],[8, 6.9]],
		 * "max": 50,"min": 0,"threshold": 8.9 } }
		 * 
		 * 
		 * {"id":1,"data":{"name":"Server room temperature trend","values":
		 * "[[1,29.2],[2,29.2],[3,29.2],[4,19.2],[5,22.2],[6,25.2],[7,26.2],[8,26.2],]",
		 * "max":30,"min":10,"threshold":25}}
		 * 
		 */
		String temp = "";

		for (ServerRoom srd : dbList) {

			humidity = srd.getTermperature();

			temp = "[";

			temp = temp + counter + "," + humidity;

			temp = temp + "]";

			System.out.println("**************** " + temp);
			dataPointsValues = dataPointsValues + temp + ",";

			counter++;
		}
		int length = dataPointsValues.length();
		dataPointsValues = dataPointsValues.substring(0, length - 1);
		dataPointsValues = dataPointsValues + "]";
		data.setValues((Object)dataPointsValues);

		System.out.println("********* Line chart data = " + dataPointsValues);
		lineChartData.setData(data);

		String result = Utility.convertObjectToString(lineChartData);
		
		
		System.out.println("*************** BEFORE  " + result);
		
		String r = result;
		
		r=r.replace("values\":\"", "values\":");
		
		r = r.replace("\",\"max\"", ",\"max\"");
		
		System.out.println("*************** AFTER " + r);
		return r;

	}


}
