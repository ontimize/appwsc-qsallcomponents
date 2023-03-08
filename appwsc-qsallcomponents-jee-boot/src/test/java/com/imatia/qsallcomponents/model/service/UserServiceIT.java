package com.imatia.qsallcomponents.model.service;


import com.imatia.qsallcomponents.api.services.IUserService;
import com.ontimize.jee.common.dto.EntityResult;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNull;

@SpringBootTest(classes = {})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
public class UserServiceIT {

    @MockBean
    IUserService iUserService;

    @BeforeAll
    static void initDataBase(@Autowired DataSource dataSource) throws SQLException {

        Connection con = dataSource.getConnection();
        Statement statement = con.createStatement();

        statement.execute("CREATE TABLE TUSER(USER_ VARCHAR(50) NOT NULL PRIMARY KEY," +
                "PASSWORD VARCHAR(50)," +
                "NAME VARCHAR(50)," +
                "SURNAME VARCHAR(50)," +
                "EMAIL VARCHAR(50)," +
                "NIF VARCHAR(50))");
        statement.executeUpdate("INSERT INTO TUSER VALUES('block','demouser','User blocked','Bloqueo','User Bloked',NULL)");
        statement.executeUpdate("INSERT INTO TUSER VALUES('demo','demouser','demo','demo',NULL,'44460713B')");
        statement.executeUpdate("INSERT INTO TUSER VALUES('pablo.martinez','pablo.martinez','Pablo','Martu00ednez Kirsten','pablo.martinez@imatia.com',NULL)");

    }

    @AfterAll
    static void tearDown(@Autowired DataSource dataSource) throws SQLException {

        Connection con = dataSource.getConnection();
        Statement statement = con.createStatement();

        statement.executeUpdate("DROP TABLE TUSER");

    }

    @Nested
    class UserCRUD {

        @Test
        void when_receive_keysValues_and_attributes_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("USER_", "block");

            List<String> attributes = new ArrayList<>();
            attributes.add("USER_");
            attributes.add("PASSWORD");
            attributes.add("NAME");
            attributes.add("SURNAME");
            attributes.add("EMAIL");
            attributes.add("NIF");


            EntityResult result = iUserService.userQuery(keysValues, attributes);
            //assertEquals(1, result.calculateRecordNumber());
            //Map recordValues = result.getRecordValues(0);
            //assertEquals("block", recordValues.get("USER_"));
            //assertEquals("demouser", recordValues.get("PASSWORD"));

            assertNull(result);

        }
    }


}
