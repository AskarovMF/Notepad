package ru.askarov.notepad.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.askarov.notepad.config.SpringConfig;

import javax.sql.DataSource;

@Import(SpringConfig.class)
public class SpringConfigTest {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/test");
        dataSource.setUsername("root");
        dataSource.setPassword("238341Qa");

        return dataSource;
    }
}
