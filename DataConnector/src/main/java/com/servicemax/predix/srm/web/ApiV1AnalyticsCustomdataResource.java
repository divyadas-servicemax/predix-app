package com.servicemax.predix.srm.web;
/*
 * Copyright (c) 2015 - 2016 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import javax.ws.rs.core.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicemax.predix.srm.model.AnalyticReadDataRequest;
import com.servicemax.predix.srm.model.AnalyticReadDataResponse;
import com.servicemax.predix.srm.model.AnalyticWriteDataRequest;
import com.servicemax.predix.srm.model.AnalyticWriteDataResponse;


@Path("/api/v2/analytics/customdata/")
@Service
public class ApiV1AnalyticsCustomdataResource {

	@Autowired
	private CustomDataResourceManager customDataResourceManager;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ApiV1AnalyticsCustomdataResource.class);


	@GET
	@Path("/test")
	public String test() {
		
		LOGGER.info("********** DIVYA getAnalyticReadDataResponse TEST ******  ");
		
		return Response.ok().entity("test").build().toString();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/read")
	public Response postRead(AnalyticReadDataRequest analyticReadDataRequest, @Context HttpHeaders httpHeaders, @Context UriInfo uriInfo) {
		
		LOGGER.info("********** DIVYA getAnalyticReadDataResponse START ******  "+analyticReadDataRequest);
		
		AnalyticReadDataResponse analyticReadDataResponse = customDataResourceManager.getAnalyticReadDataResponse(analyticReadDataRequest);
		return Response.ok().entity(analyticReadDataResponse).build();
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/write")
	public Response postWrite(AnalyticWriteDataRequest analyticWriteDataRequest, @Context HttpHeaders httpHeaders, @Context UriInfo uriInfo) {
		
		LOGGER.info("********** DIVYA getAnalyticReadDataResponse START ******  "+analyticWriteDataRequest);
		
		AnalyticWriteDataResponse analyticWriteDataResponse = customDataResourceManager.getAnalyticWriteDataResponse(analyticWriteDataRequest);
		return Response.status(Response.Status.CREATED).entity(analyticWriteDataResponse).build();
	}

	@GET
	@Path("/healthcheck")
	public Response healthcheck(@Context HttpHeaders httpHeaders, @Context UriInfo uriInfo) {
		return Response.status(Response.Status.OK).build();
	}
}
