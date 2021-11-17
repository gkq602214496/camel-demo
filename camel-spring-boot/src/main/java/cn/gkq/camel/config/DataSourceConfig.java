package cn.gkq.camel.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
//import org.apache.camel.spring.boot.SpringBootCamelContext;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author GKQ
 * @Classname DataSourceConfig
 * @Description DataSource 数据源配置
 * @Date 2021/3/26
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DataSourceConfig {

//    @Autowired
//    private SpringBootCamelContext camelContext;

    private String driverClassName;
    private String url;
    private String username;
    private String password;



    @Bean("myDataSource")
    @Primary
    public DataSource myDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        //注册到camel context
//        camelContext.getRegistry().bind("myDataSource", druidDataSource);
        return druidDataSource;
    }
}
