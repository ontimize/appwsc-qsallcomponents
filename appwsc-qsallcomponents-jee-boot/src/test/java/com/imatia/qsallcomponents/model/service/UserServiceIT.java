package com.imatia.qsallcomponents.model.service;


import com.imatia.qsallcomponents.api.services.IUserService;
import com.imatia.qsallcomponents.model.dao.CustomerAccountDao;
import com.imatia.qsallcomponents.model.dao.CustomerDao;
import com.imatia.qsallcomponents.model.dao.CustomerTypeDao;
import com.imatia.qsallcomponents.model.dao.UserDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.services.dms.IDMSService;
import com.ontimize.jee.server.services.dms.DMSCreationHelper;
import org.junit.jupiter.api.*;
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
import java.sql.Timestamp;
import java.util.*;

import static org.junit.Assert.*;

@SpringBootTest(classes = {CustomerService.class,
        CustomerDao.class,
        CustomerTypeDao.class,
        CustomerAccountDao.class,
        UserDao.class,
        UserService.class,
        DMSCreationHelper.class})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceIT {

    @Autowired
    IUserService iUserService;

    @Autowired
    DataSource dataSource;


    @MockBean
    IDMSService dmsService;


    @BeforeAll
    void initDataBase() throws SQLException {

        Connection con = dataSource.getConnection();
        Statement statement = con.createStatement();

        statement.execute("CREATE TABLE TUSER(USER_ VARCHAR(50) NOT NULL PRIMARY KEY," +
                "PASSWORD VARCHAR(50)," +
                "NAME VARCHAR(50)," +
                "SURNAME VARCHAR(50)," +
                "EMAIL VARCHAR(50)," +
                "NIF VARCHAR(50)," +
                "DOWN_DATE TIMESTAMP)");
        statement.executeUpdate("INSERT INTO TUSER VALUES('block','demouser','User blocked','Bloqueo','User Bloked',NULL, NULL)");
        statement.executeUpdate("INSERT INTO TUSER VALUES('demo','demouser','demo','demo',NULL,'44460713B', NULL)");
        statement.executeUpdate("INSERT INTO TUSER VALUES('pablo.martinez','pablo.martinez','Pablo','Martinez Kirsten','pablo.martinez@imatia.com',NULL, NULL)");

    }

    @AfterAll
    void tearDown() throws SQLException {

        Connection con = dataSource.getConnection();
        Statement statement = con.createStatement();

        statement.executeUpdate("DROP TABLE TUSER");

    }

    @Nested
    class UserCRUD {

        @Test
        void when_userQuery_receive_keysValues_and_attributes_expected_EntityResult() {
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
            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("block", recordValues.get("USER_"));
            assertEquals("demouser", recordValues.get("PASSWORD"));

        }

        @Test
        void when_userInsert_receive_attrMap_expected_EntityResult() {
            Map<String, Object> attrMap = new HashMap<>();
            attrMap.put("USER_", "cuatro");
            attrMap.put("PASSWORD", "password");
            attrMap.put("NAME", "name");
            attrMap.put("SURNAME","surname");

            iUserService.userInsert(attrMap);

            List<String> attributes = new ArrayList<>();
            attributes.add("USER_");
            attributes.add("PASSWORD");
            attributes.add("NAME");
            attributes.add("SURNAME");
            attributes.add("EMAIL");
            attributes.add("NIF");

            EntityResult result = iUserService.userQuery(attrMap, attributes);
            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("cuatro", recordValues.get("USER_"));
            assertEquals("password", recordValues.get("PASSWORD"));

        }


        @Test
        void when_userUpdate_receive_attrMap_and_keyMap_expected_EntityResult() {
            Map<String, Object> keyMap = new HashMap<>();
            keyMap.put("USER_", "demo");
            keyMap.put("PASSWORD", "update");

            Map<String, Object> attrMap = new HashMap<>();
            attrMap.put("PASSWORD", "update");

            iUserService.userUpdate(attrMap,keyMap);

            List<String> attributes = new ArrayList<>();
            attributes.add("USER_");
            attributes.add("PASSWORD");
            attributes.add("NAME");
            attributes.add("SURNAME");
            attributes.add("EMAIL");
            attributes.add("NIF");

            EntityResult result = iUserService.userQuery(keyMap, attributes);
            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("demo", recordValues.get("USER_"));
            assertEquals("update", recordValues.get("PASSWORD"));

        }

        @Test
        void when_userDelete_receive_keyMap_expected_EntityResult() {
            Map<String, Object> keyMap = new HashMap<>();
            keyMap.put("USER_", "pablo.martinez");
            keyMap.put("PASSWORD", "pablo.martinez");

            iUserService.userDelete(keyMap);

            List<String> attributes = new ArrayList<>();
            attributes.add("USER_");
            attributes.add("PASSWORD");
            attributes.add("NAME");
            attributes.add("SURNAME");
            attributes.add("EMAIL");
            attributes.add("NIF");
            attributes.add("DOWN_DATE");

            EntityResult result = iUserService.userQuery(keyMap, attributes);
            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("pablo.martinez", recordValues.get("USER_"));
            assertNotNull("DOWN_DATE");

        }
    }


}
