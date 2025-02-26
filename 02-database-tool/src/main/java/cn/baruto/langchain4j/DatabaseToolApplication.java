package cn.baruto.langchain4j;

import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SpringBootApplication
public class DatabaseToolApplication implements CommandLineRunner {

    public static void main(String[] args) {
        System.setProperty("http.proxyHost","127.0.0.1");
        System.setProperty("http.proxyPort","7890");
        SpringApplication.run(DatabaseToolApplication.class, args);
    }

    @Resource
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from user");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("user_id"));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
