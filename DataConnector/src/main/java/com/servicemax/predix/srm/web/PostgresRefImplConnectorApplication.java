package com.servicemax.predix.srm.web;


public class PostgresRefImplConnectorApplication {
	
}
/*
import java.util.Collections;
import javax.sql.DataSource;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@ComponentScan(basePackages = "com.servicemax.predix.srm")
@Configuration
@EnableAutoConfiguration
@ImportResource(value = "classpath:META-INF/authentication-context.xml")
//@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
//@PropertySource(value = "custom-connector-application.properties")
public class PostgresRefImplConnectorApplication {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	private Bus bus;

	public static void main(String[] args) {
		SpringApplication.run(PostgresRefImplConnectorApplication.class, args);
	}

	@Bean
	public Server rsServer() {
		JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
		endpoint.setBus(bus);
		endpoint.setServiceBeans(Collections.<Object>singletonList(applicationContext.getBeansWithAnnotation(Path.class).values()));
		endpoint.setProviders(Collections.<Object>singletonList(applicationContext.getBeansWithAnnotation(Provider.class).values()));
		endpoint.setAddress("/");
		return endpoint.create();
	}

	/*@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}*/
/*
	@Bean
	public JacksonJsonProvider jacksonJsonProvider() {
		JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jacksonJsonProvider.setMapper(mapper);
		return jacksonJsonProvider;
	}
}*/
