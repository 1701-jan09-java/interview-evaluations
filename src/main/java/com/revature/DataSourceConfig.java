package com.revature;

import javax.activation.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataSourceConfig {
	
	 @Bean
	    public DataSource dataSource() {
	        return (DataSource) new EmbeddedDatabaseBuilder()
	            .generateUniqueName(true)
	            .setType(EmbeddedDatabaseType.H2)
	            .setScriptEncoding("UTF-8")
	            .ignoreFailedDrops(true)
	            .addScripts("create-person.sql", "insert-person-data.sql")
	            .build();
	    }

}
