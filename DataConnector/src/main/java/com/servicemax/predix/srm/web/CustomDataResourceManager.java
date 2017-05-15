
package com.servicemax.predix.srm.web;

import static java.text.MessageFormat.format;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.servicemax.predix.srm.model.AnalyticReadDataRequest;
import com.servicemax.predix.srm.model.AnalyticReadDataResponse;
import com.servicemax.predix.srm.model.AnalyticWriteDataRequest;
import com.servicemax.predix.srm.model.AnalyticWriteDataResponse;
import com.servicemax.predix.srm.model.Field;
import com.servicemax.predix.srm.model.OrchestrationExecutionContext;
import com.servicemax.predix.srm.model.errorresponse.ErrorResponse;
import com.servicemax.predix.srm.service.SaveResponseService;

@Component
@ComponentScan
public class CustomDataResourceManager {

	private final static Logger LOGGER = LoggerFactory.getLogger(CustomDataResourceManager.class);

	private enum ErrorCode {
		FIELD_EXCEPTION,
		GENERAL_EXCEPTION

	}
	
	@Autowired
	SaveResponseService saveRS;

	/*final private JdbcTemplate jdbcTemplate;

	@Autowired
	public CustomDataResourceManager(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}*/

	AnalyticReadDataResponse getAnalyticReadDataResponse(final AnalyticReadDataRequest analyticReadDataRequest) {
		LOGGER.info("********** DIVYA getAnalyticReadDataResponse START ******  "+analyticReadDataRequest);
		
		AnalyticReadDataResponse analyticReadDataResponse = new AnalyticReadDataResponse();
		analyticReadDataResponse.setOrchestrationExecutionContext(analyticReadDataRequest.getOrchestrationExecutionContext());
		analyticReadDataResponse.getField().addAll(analyticReadDataRequest.getField());
		
		analyticReadDataResponse.setDataSourceId(analyticReadDataRequest.getDataSourceId());

		Map<String, String> customAttributes = convertAttributes(analyticReadDataRequest.getCustomAttributes());
		Map<String, String> systemAttributes = convertAttributes(analyticReadDataRequest.getSystemAttributes());

		LOGGER.info("Processing read request...");
		LOGGER.info("data source id : " + analyticReadDataRequest.getDataSourceId());
		LOGGER.info("custom attributes : " + customAttributes.toString());
		LOGGER.info("system attributes : " + systemAttributes.toString());

		
		boolean isGenericSchema = true;
		try {
			List<Field> fieldList = analyticReadDataResponse.getField();
			for (Field field : fieldList) {
				retrieveDataForField(field, analyticReadDataRequest.getOrchestrationExecutionContext(), isGenericSchema);
			}
		} catch (Exception e) {
			String message = format("Unknown error occured {0}", e.getMessage());
			LOGGER.error(message, e);
			analyticReadDataResponse.setErrorResponse(createErrorResponse(ErrorCode.GENERAL_EXCEPTION, message));
		}
		
		LOGGER.info("********** DIVYA getAnalyticReadDataResponse END ******  "+analyticReadDataResponse);
		return analyticReadDataResponse;
	}

	AnalyticWriteDataResponse getAnalyticWriteDataResponse(AnalyticWriteDataRequest analyticWriteDataRequest) {
		LOGGER.info("********** DIVYA getAnalyticWriteDataResponse START ******  ");
		LOGGER.info("**********  ******  "+analyticWriteDataRequest);
		LOGGER.info("********** DIVYA getAnalyticWriteDataResponse INPUT ******  "+analyticWriteDataRequest);
		

		AnalyticWriteDataResponse analyticWriteDataResponse = new AnalyticWriteDataResponse();
		analyticWriteDataResponse.setOrchestrationExecutionContext(analyticWriteDataRequest.getOrchestrationExecutionContext());
		analyticWriteDataResponse.getField().addAll(analyticWriteDataRequest.getField());

		//Map<String, String> customAttributes = convertAttributes(analyticWriteDataRequest.getCustomAttributes());
		//Map<String, String> systemAttributes = convertAttributes(analyticWriteDataRequest.getSystemAttributes());

		LOGGER.info("Processing write request...");
		LOGGER.info("data source id : " + analyticWriteDataRequest.getDataSourceId());
		//LOGGER.info("custom attributes : " + customAttributes.toString());
		//LOGGER.info("system attributes : " + systemAttributes.toString());

		boolean isGenericSchema = true;
		try {
			List<Field> fieldList = analyticWriteDataResponse.getField();
			for (Field field : fieldList) {
				insertOrUpdateFieldWithData(field, analyticWriteDataRequest.getOrchestrationExecutionContext(), isGenericSchema);
			}
		} catch (Exception e) {
			String message = format("Unknown error occured {0}", e.getMessage());
			LOGGER.error(message, e);
			analyticWriteDataResponse.setErrorResponse(createErrorResponse(ErrorCode.GENERAL_EXCEPTION, message));
		}
		return analyticWriteDataResponse;
	
		
	}

	private Map<String, String> convertAttributes(Object customObject) {
		if (customObject == null) {
			return new HashMap<>();
		}
		return (Map<String, String>) customObject;
	}
	
	protected static int getRandomNumber(){
		Random r = new Random();
		int Low = 1;
		int High = 500;
		int randomInteger = r.nextInt(High-Low) + Low;
		return randomInteger;
	}
	
	/*protected static String getRandomString(){
		Random r = new Random();
		int Low = 1;
		int High = 6;
		int randomInteger = r.nextInt(High-Low) + Low;
		Map<Integer,String> textMap = loadTextMap();
		System.out.println(randomInteger);
		return textMap.get(randomInteger);
	}
	
	protected static Map<Integer,String> loadTextMap(){
		Map<Integer,String> textMap = new HashMap<>();
		textMap.put(1, "Hello   %^%^ how are 56 you23#$");
		textMap.put(2, "How to $%$ remov$e special&*&*  characters!!!  ");
		textMap.put(3, "You1 can implement@@ more! than one*& Custom Data123 Connector service1");
		textMap.put(4, "The service must **implement the @@following 1three Analytics 12Runtime APIs.   ");
		textMap.put(5, "The   REST1 end points^%^ are secured!! using@@ custom#$# UAA-based authentication!@#$. ");
		
		return textMap;
	}*/

	private void insertOrUpdateFieldWithData(Field field, OrchestrationExecutionContext orchestrationExecutionContext, boolean isGenericSchema) {
		//save the sum in database
		int sum = Integer.parseInt(field.getData().toString());
		saveRS.insertSumResultData(sum);
	}

	private void retrieveDataForField(Field field, OrchestrationExecutionContext orchestrationExecutionContext, boolean isGenericSchema) {
		//set the number1 and number2
		
		field.setData(getRandomNumber());
	}

	

	

	private ErrorResponse createErrorResponse(ErrorCode code, String message) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode(code.name());
		errorResponse.setMessage(message);
		return errorResponse;
	}
/*
	private boolean isGenericSchema(Map<String, String> customAttributes) {
		LOGGER.info("**** isGenericSchemaAttrValue ****");
		String isGenericSchemaAttrValue = customAttributes.get("IS_GENERIC_SCHEMA");
		if ("TRUE".equalsIgnoreCase(isGenericSchemaAttrValue)) {
			return true;
		}
		return false;
	}*/

}
