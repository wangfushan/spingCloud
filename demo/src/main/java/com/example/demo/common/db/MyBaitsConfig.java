package com.example.demo.common.db;

import com.github.pagehelper.PageInterceptor;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
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
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class MyBaitsConfig implements TransactionManagementConfigurer {

    @Value("${spring.datasource.username}")
    private String rwName;

    @Value("${spring.datasource.driver-class-name}")
    private String rwClassName;

    @Value("${spring.datasource.url}")
    private String rwUrl;

    @Value("${spring.datasource.username}")
    private String rwUsername;

    @Value("${spring.datasource.password}")
    private String rwPassword;

    @Value("${spring.datasource.tomcat.max-active}")
    private int rwMaxPoolSize;

    @Value("${spring.datasource.tomcat.min-idle}")
    private int rwMinPoolSize;

    @Value("${spring.datasource.initialPoolSize}")
    private int rwInitialPoolSize;

    @Value("${spring.jta.atomikos.datasource.max-idle-time}")
    private int rwMaxIdleTime;

    /**
     * 配置dataSource，使用C3P0连接池
     *
     * @throws PropertyVetoException
     */
    @Bean(destroyMethod = "close")
    @Primary
    public DataSource dataSource1() throws PropertyVetoException {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDataSourceName(rwName);
        dataSource.setDriverClass(rwClassName);
        dataSource.setJdbcUrl(rwUrl);
        dataSource.setUser(rwUsername);
        dataSource.setPassword(rwPassword);
        dataSource.setMaxPoolSize(Integer.valueOf(rwMaxPoolSize));
        dataSource.setMinPoolSize(Integer.valueOf(rwMinPoolSize));
        dataSource.setInitialPoolSize(Integer.valueOf(rwInitialPoolSize));
        dataSource.setMaxIdleTime(Integer.valueOf(rwMaxIdleTime));
        return dataSource;
    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() {
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource1());
            // 分页插件

            Properties properties = new Properties();
            properties.setProperty("supportMethodsArguments", "true");
            properties.setProperty("returnPageInfo", "check");
            // 添加插件
            Interceptor i = new PageInterceptor();
            i.setProperties(properties);
            bean.setPlugins(new Interceptor[] { i });

            // 指定mapper xml目录
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        try {
            return new DataSourceTransactionManager(dataSource1());
        } catch (PropertyVetoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
