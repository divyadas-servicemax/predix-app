package com.servicemax.predix.srm.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicemax.predix.srm.domain.Responsedata;
import com.servicemax.predix.srm.web.CustomDataResourceManager;

@Service
@SuppressWarnings("unchecked")
@Transactional
public class SaveResponseService {

	private final static Logger LOGGER = LoggerFactory.getLogger(SaveResponseService.class);
	
	@PersistenceContext
	private EntityManager em;

	public void insertSumResultData(int sum) {
		LOGGER.info("**********  in SaveResponseService.insertSumResultData  **********  EntityManager = "+em+"\n\n\n  sum = "+sum);
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date today = Calendar.getInstance().getTime();
			String currentTimeString = df.format(today);
			Responsedata rd = new Responsedata();
			rd.setSum(sum);
			rd.setTimeStamp(currentTimeString);
			em.persist(rd);
			
			LOGGER.info("********  DB ROW IS = "+rd);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
