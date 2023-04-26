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
 * Data:2023/4/25 14:23
 *
 */
@Configuration
public class OracleDataSourceConfig {

    @Bean(name = "oracleDataSource")
    @Qualifier("oracleDataSource")
    @ConfigurationProperties(prefix="spring.datasource.datasourcea")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }
}