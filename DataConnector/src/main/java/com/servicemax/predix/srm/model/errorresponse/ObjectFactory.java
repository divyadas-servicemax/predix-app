
package com.servicemax.predix.srm.model.errorresponse;

import javax.xml.bind.annotation.XmlRegistry;

import com.servicemax.predix.srm.model.AnalyticReadDataRequest;
import com.servicemax.predix.srm.model.AnalyticReadDataResponse;
import com.servicemax.predix.srm.model.AnalyticWriteDataRequest;
import com.servicemax.predix.srm.model.AnalyticWriteDataResponse;
import com.servicemax.predix.srm.model.DataRequest;
import com.servicemax.predix.srm.model.DataResponse;
import com.servicemax.predix.srm.model.Field;
import com.servicemax.predix.srm.model.OrchestrationExecutionContext;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ge.predix.insight.dto.customdataprovider package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for 
     * package: com.ge.predix.insight.dto.customdataprovider
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AnalyticReadDataRequest }
     * 
     */
    public AnalyticReadDataRequest createAnalyticReadDataRequest() {
        return new AnalyticReadDataRequest();
    }

    /**
     * Create an instance of {@link DataRequest }
     * 
     */
    public DataRequest createDataRequest() {
        return new DataRequest();
    }

    /**
     * Create an instance of {@link Field }
     * 
     */
    public Field createField() {
        return new Field();
    }

    /**
     * Create an instance of {@link OrchestrationExecutionContext }
     * 
     */
    public OrchestrationExecutionContext createOrchestrationExecutionContext() {
        return new OrchestrationExecutionContext();
    }

    /**
     * Create an instance of {@link AnalyticReadDataResponse }
     * 
     */
    public AnalyticReadDataResponse createAnalyticReadDataResponse() {
        return new AnalyticReadDataResponse();
    }

    /**
     * Create an instance of {@link DataResponse }
     * 
     */
    public DataResponse createDataResponse() {
        return new DataResponse();
    }

    /**
     * Create an instance of {@link AnalyticWriteDataRequest }
     * 
     */
    public AnalyticWriteDataRequest createAnalyticWriteDataRequest() {
        return new AnalyticWriteDataRequest();
    }

    /**
     * Create an instance of {@link AnalyticWriteDataResponse }
     * 
     */
    public AnalyticWriteDataResponse createAnalyticWriteDataResponse() {
        return new AnalyticWriteDataResponse();
    }

}
