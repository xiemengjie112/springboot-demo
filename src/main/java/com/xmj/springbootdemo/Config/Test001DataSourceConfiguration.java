package com.xmj.springbootdemo.Config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2018/11/10 21:05
 */
@Configuration
@MapperScan(basePackages ="com.xmj.springbootdemo.mapper.test001.**",sqlSessionTemplateRef = "test001SqlSessionTemplate")
public class Test001DataSourceConfiguration {

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "test001DataSource")
    @Qualifier("test001DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test001")
    public DataSource secondaryDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "test001SqlSessionFactory")
    @Qualifier("test001SqlSessionFactory")
    public SqlSessionFactory test001SqlSessionFactory(@Qualifier("test001DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:**/Mapper/test001/*.xml"));
        bean.setTypeAliasesPackage("com.xmj.springbootdemo.entity.test001");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCallSettersOnNulls(true);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean(name = "test001SqlSessionTemplate")
    public SqlSessionTemplate test001SqlSessionTemplate(@Qualifier("test001SqlSessionFactory") SqlSessionFactory test001SqlSessionFactory) {
        SqlSessionTemplate template = new SqlSessionTemplate(test001SqlSessionFactory); // 使用上面配置的Factory
        return template;
    }

    /******配置事务管理********/
    @Bean(name="test001Transaction")
    public PlatformTransactionManager test001TransactionManager(@Qualifier("test001DataSource")DataSource prodDataSource) {
        return new DataSourceTransactionManager(prodDataSource);
    }

}
