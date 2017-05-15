package com.servicemax.predix.srm.web;

import java.util.Date;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.servicemax.predix.srm.model.AnalyticReadDataRequest;
import com.servicemax.predix.srm.model.AnalyticReadDataResponse;
import com.servicemax.predix.srm.model.AnalyticWriteDataRequest;
import com.servicemax.predix.srm.model.AnalyticWriteDataResponse;

@ComponentScan
@RestController
public class SRMApiController {

	private final static Logger LOGGER = LoggerFactory.getLogger(SRMApiController.class);

	@Autowired
	private CustomDataResourceManager customDataResourceManager;

	public static final String INSERT = "/insertServerData";
	public static final String GETALL = "/getAll";
	public static final String TEST = "/test";

	@RequestMapping(value = TEST, method = RequestMethod.GET)
	public String testApplication() throws Exception {

		return "Application is up and running. " + new Date().toString();
	}

	@RequestMapping(value = "/dummy", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody String addServerRoomData(@RequestBody String jsonInput) {
		System.out.println("**** In controller insert method , input = " + jsonInput);
		return "POST working fine";
		// return "got input as "+jsonInput;
	}

	// @POST
	// @Consumes("application/json")
	// @Produces("application/json")
	// @Path("/read")
	@RequestMapping(value = "/api/v1/analytics/customdata/read", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Response postRead(@RequestBody AnalyticReadDataRequest analyticReadDataRequest){
			//, @Context HttpHeaders httpHeaders, @Context UriInfo uriInfo) {
		LOGGER.info("********* DIVYA IN READ API ******* " + analyticReadDataRequest);
		AnalyticReadDataResponse analyticReadDataResponse = customDataResourceManager
				.getAnalyticReadDataResponse(analyticReadDataRequest);
		return Response.ok().entity(analyticReadDataResponse).build();
	}

	@RequestMapping(value = "/api/v1/analytics/customdata/write", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Response postWrite(@RequestBody AnalyticWriteDataRequest analyticWriteDataRequest){
			//, @Context HttpHeaders httpHeaders, @Context UriInfo uriInfo) {
		// public Response postWrite(@RequestBody AnalyticReadDataRequest
		// analyticReadDataRequest) {
		LOGGER.info("********* DIVYA IN WRITE API ******* ");
		LOGGER.info("********************  " + analyticWriteDataRequest);
		AnalyticWriteDataResponse analyticWriteDataResponse = customDataResourceManager
				.getAnalyticWriteDataResponse(analyticWriteDataRequest);
		return Response.status(Response.Status.CREATED).entity(analyticWriteDataResponse).build();
	}

	@RequestMapping(value = "/api/v1/analytics/customdata/healthcheck", method = RequestMethod.GET)
	public Response healthcheck() {
		LOGGER.info("********* DIVYA IN HEALTHCHECK API ******* ");
		return Response.status(Response.Status.OK).build();
	}

	/*
	 * @RequestMapping(value = GETALL, method = RequestMethod.GET) public String
	 * getAllServerRoomData() throws Exception { String serverRoomDataList =
	 * serverRoomService.getAllServerRoomData(); return serverRoomDataList; }
	 * 
	 * @RequestMapping(value = INSERT, method = RequestMethod.POST, consumes =
	 * "application/json", produces = "text/plain") public @ResponseBody String
	 * addServerRoomData(@RequestBody String jsonInput) {
	 * System.out.println("**** In controller insert method , input = " +
	 * jsonInput); return serverRoomService.insertServerDataJson(jsonInput); //
	 * return "got input as "+jsonInput; }
	 * 
	 * @RequestMapping(value = "/insert", method = RequestMethod.POST) public
	 * String addServerRoomData1(@RequestBody Input input) {
	 * System.out.println("**** In controller1 insert method , input = " +
	 * input); return serverRoomService.insertServerData(input); }
	 * 
	 * @RequestMapping(value = "/dummy", method = RequestMethod.GET) public
	 * String getDummyData() throws Exception { String serverRoomDataList =
	 * "{\"id\": 2,\"data\": {\"name\": " +
	 * "\"Output vs. Capacity 3\",\"values\": [[1, 2],[2, 1], [3, 4], [4, 3.9],[5, 4.5],[6, 4.2], [7, 1.5],[8, 6.9]],\"max\": 50,\"min\": 0,\"threshold\": 8.9 }}"
	 * ; return serverRoomDataList; }
	 * 
	 * @RequestMapping(value = "/getTemperatureData", method =
	 * RequestMethod.GET) public String getAllServerRoomTempData() throws
	 * Exception {
	 * System.out.println("*********  In getAllServerRoomTempData "); String
	 * serverRoomDataList = serverRoomService.getAllServerRoomTempData(); return
	 * serverRoomDataList; }
	 * 
	 * @RequestMapping(value = "/getHumidityData", method = RequestMethod.GET)
	 * public String getAllServerRoomHumidityData() throws Exception { String
	 * serverRoomDataList = serverRoomService.getAllServerRoomHumidityData();
	 * return serverRoomDataList; }
	 */

}