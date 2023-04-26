package wjz.utilproject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/25 14:24
 *
 */
@Configuration
public class MysqlDataSourceConfig {

    @Bean(name = "mysqlDataSource")
    @Qualifier("mysqlDataSource")
    @ConfigurationProperties(prefix="spring.datasource.datasourceb")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }
}