package com.project.catcaring.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.project.catcaring.mapper")
@RequiredArgsConstructor
public class DatabaseConfig {

  private final ApplicationContext applicationContext;

  @Bean
  public SqlSessionFactory  sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*.xml"));
    return factoryBean.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate (SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}
