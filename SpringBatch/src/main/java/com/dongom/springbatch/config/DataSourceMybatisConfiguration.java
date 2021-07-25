package com.dongom.springbatch.config;

import javax.sql.DataSource;

import org.apache.catalina.core.ApplicationContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceMybatisConfiguration {
	
	 @Bean(MASTER_DATASOURCE)
	    @Primary
	    @ConfigurationProperties(prefix = PROPERTIES)
	    public DataSource dataSource() {
	        return DataSourceBuilder.create()
	                .type(HikariDataSource.class)
	                .build();
	    }
	    
	    @Bean(name="sqlSessionFactory")
	    @Primary
	    public SqlSessionFactory dbSqlSessionFactory(@Qualifier("dataSource") DataSource dbDataSource, ApplicationContext applicationContext) throws Exception {
	    	 SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	    	 sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
	         sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
	         return sqlSessionFactoryBean.getObject();
	    }
	    
	    @Bean(name="sqlSessionTemplate")
	    @Primary
	    public SqlSessionTemplate dbSqlsessionTemplate() throws Exception {
	    	return new SqlSessionTemplate(dbSqlSessionFactory);
	    }

}
