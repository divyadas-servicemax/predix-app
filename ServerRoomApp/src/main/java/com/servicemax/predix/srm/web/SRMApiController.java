package com.servicemax.predix.srm.web;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.servicemax.predix.srm.model.Input;
import com.servicemax.predix.srm.service.ServerRoomService;

@ComponentScan
@RestController
public class SRMApiController {

	@Autowired
	private ServerRoomService serverRoomService;

	public static final String INSERT = "/insertServerData";
	public static final String GETALL = "/getAll";
	public static final String TEST = "/test";

	@RequestMapping(value = TEST, method = RequestMethod.GET)
	public String testApplication() throws Exception {

		return "Application is up and running.";
	}

	@RequestMapping(value = GETALL, method = RequestMethod.GET)
	public String getAllServerRoomData() throws Exception {
		String serverRoomDataList = serverRoomService.getAllServerRoomData();
		return serverRoomDataList;
	}

	@RequestMapping(value = INSERT, method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public @ResponseBody String addServerRoomData(@RequestBody String jsonInput) {
		System.out.println("**** In controller insert method , input = " + jsonInput);
		return serverRoomService.insertServerDataJson(jsonInput);
		// return "got input as "+jsonInput;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String addServerRoomData1(@RequestBody Input input) {
		System.out.println("**** In controller1 insert method , input = " + input);
		return serverRoomService.insertServerData(input);
	}

	@RequestMapping(value = "/dummy", method = RequestMethod.GET)
	public String getDummyData() throws Exception {
		String serverRoomDataList = "{\"id\": 2,\"data\": {\"name\": "
				+ "\"Output vs. Capacity 3\",\"values\": [[1, 2],[2, 1], [3, 4], [4, 3.9],[5, 4.5],[6, 4.2], [7, 1.5],[8, 6.9]],\"max\": 50,\"min\": 0,\"threshold\": 8.9 }}";
		return serverRoomDataList;
	}

	@RequestMapping(value = "/getTemperatureData", method = RequestMethod.GET)
	public String getAllServerRoomTempData() throws Exception {
		System.out.println("*********  In getAllServerRoomTempData ");
		String serverRoomDataList = serverRoomService.getAllServerRoomTempData();
		return serverRoomDataList;
	}

	@RequestMapping(value = "/getHumidityData", method = RequestMethod.GET)
	public String getAllServerRoomHumidityData() throws Exception {
		String serverRoomDataList = serverRoomService.getAllServerRoomHumidityData();
		return serverRoomDataList;
	}

}