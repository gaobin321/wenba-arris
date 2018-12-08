package com.wenba.arris;

import com.wenba.arris.common.annotation.Master;
import com.wenba.arris.common.annotation.Slave;
import com.wenba.arris.common.utils.db.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
//@AutoConfigureBefore({ ServletEndpointRegistrar.class })
public class DataSourceConfig implements TransactionManagementConfigurer {
    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    private String mapperLocation = "classpath:mapper/**/*.xml";
    private String domainPackage = "com.wenba.arris.domain";

    @Primary
    @Bean(name = "wenba-master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource getWenBaMaster() {
        return DataSourceBuilder.create().type(this.dataSourceType).build();
    }

    @Bean(name = "wenba-slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource getWenBaSlaveOne() {
        return DataSourceBuilder.create().type(this.dataSourceType).build();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource());
        bean.setTypeAliasesPackage(domainPackage);

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources(mapperLocation));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Override
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    @Bean
    public DynamicDataSource dynamicDataSource() {
        DataSource master = getWenBaMaster();
        DataSource slave = getWenBaSlaveOne();
        Map<Object, Object> targetDataSources = new HashMap();
        targetDataSources.put(Master.class, master);
        targetDataSources.put(Slave.class, slave);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources); // 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(master);
        return dataSource;
    }

    public Class<? extends DataSource> getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(Class<? extends DataSource> dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public String getMapperLocation() {
        return mapperLocation;
    }

    public void setMapperLocation(String mapperLocation) {
        this.mapperLocation = mapperLocation;
    }

    public String getDomainPackage() {
        return domainPackage;
    }

    public void setDomainPackage(String domainPackage) {
        this.domainPackage = domainPackage;
    }
}
