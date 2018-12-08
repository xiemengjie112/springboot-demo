package com.xmj.springbootdemo.config;

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
import org.springframework.context.annotation.Primary;
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
@MapperScan(basePackages ="com.xmj.springbootdemo.mapper.student.**",sqlSessionTemplateRef = "studentSqlSessionTemplate")
public class StudentDataSourceConfiguration {

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "studentDataSource")
    @Qualifier("studentDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.student")
    public DataSource secondaryDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "studentSqlSessionFactory")
    @Qualifier("studentSqlSessionFactory")
    public SqlSessionFactory studentSqlSessionFactory(@Qualifier("studentDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:**/Mapper/student/*.xml"));
        bean.setTypeAliasesPackage("com.xmj.springbootdemo.entity.student");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCallSettersOnNulls(true);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean(name = "studentSqlSessionTemplate")
    public SqlSessionTemplate studentSqlSessionTemplate(@Qualifier("studentSqlSessionFactory") SqlSessionFactory studentSqlSessionFactory) {
        SqlSessionTemplate template = new SqlSessionTemplate(studentSqlSessionFactory); // 使用上面配置的Factory
        return template;
    }

    /******配置事务管理********/
    @Bean(name="studentTransaction")
    public PlatformTransactionManager vts2TransactionManager(@Qualifier("studentDataSource")DataSource prodDataSource) {
        return new DataSourceTransactionManager(prodDataSource);
    }


}
