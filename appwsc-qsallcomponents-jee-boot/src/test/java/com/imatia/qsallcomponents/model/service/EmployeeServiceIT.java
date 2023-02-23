package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.api.services.IEmployeeService;
import com.imatia.qsallcomponents.model.dao.EmployeeDao;
import com.imatia.qsallcomponents.model.dao.EmployeeTypeDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {
        EmployeeService.class,
        EmployeeDao.class,
        EmployeeTypeDao.class
})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@TestInstance(Lifecycle.PER_CLASS)
public class EmployeeServiceIT {

    @Autowired
    IEmployeeService iemployeeService;


    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DataSource dataSource;

    @BeforeAll
    void initDataBase() throws SQLException {

        dataSource.getConnection().createStatement().execute("CREATE TABLE EMPLOYEES (EMPLOYEEID INT NOT NULL PRIMARY KEY, NAME VARCHAR(50) NOT NULL,"
                + "EMAIL VARCHAR(50) NOT NULL, OFFICEID VARCHAR(50))");

        dataSource.getConnection().createStatement().executeUpdate("INSERT INTO EMPLOYEES VALUES (1001,'Vinod', 'vinod@imatia.com', 0001)");
        dataSource.getConnection().createStatement().executeUpdate("INSERT INTO EMPLOYEES VALUES (1002,'Dhwani', 'dhwani@imatia.com', 0002)");
        dataSource.getConnection().createStatement().executeUpdate("INSERT INTO EMPLOYEES VALUES (1003,'Asmi', 'asmi@imatia.com', 0003)");
        /*dataSource.getConnection().prepareStatement("INSERT INTO EMPLOYEES VALUES (1004,'Caroline', 'caroline@imatia.com', 0003)");
        dataSource.getConnection().prepareStatement("INSERT INTO EMPLOYEES VALUES (1005,'Cris', 'cris@imatia.com', 0003)");
        dataSource.getConnection().prepareStatement("INSERT INTO EMPLOYEES VALUES (1006,'Mark', 'mark@imatia.com', 0003)");*/

        dataSource.getConnection().createStatement().execute("CREATE TABLE BRANCHES(OFFICEID VARCHAR(50) NOT NULL PRIMARY KEY,NAME VARCHAR(50),"
                + "ADDRESS VARCHAR(255),PHONE VARCHAR(50))");
        dataSource.getConnection().createStatement().executeUpdate("INSERT INTO BRANCHES VALUES (0001,'IMATIABANK', 'VIGO', 012012012)");
        dataSource.getConnection().createStatement().executeUpdate("INSERT INTO BRANCHES VALUES (0002,'KUTXA', 'PONTEVEDRA', 234234234)");
        dataSource.getConnection().createStatement().executeUpdate("INSERT INTO BRANCHES VALUES (0003,'ABANCA', 'OURENSE', 57567567)");


    }

    @AfterAll
    void tearDown() throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate("DROP TABLE EMPLOYEES");
        dataSource.getConnection().createStatement().executeUpdate("DROP TABLE BRANCHES");

    }


    @Nested
    class EmployeeCRUD {


        @Test
        void when_employeeQuery_receive_keysValues_and_attributes_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap();
            keysValues.put("EMPLOYEEID", 1001);

            List<String> attributes = new ArrayList();
            attributes.add("EMPLOYEEID");
            attributes.add("NAME");
            attributes.add("EMAIL");
            attributes.add("OFFICEID");

            EntityResult result = iemployeeService.employeeQuery(keysValues, attributes);

            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);

            assertEquals(1001, recordValues.get("EMPLOYEEID"));

            assertEquals("Vinod", recordValues.get("NAME"));

        }


        @Test
        void when_employeePaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvacedEntityResult() {

            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("EMPLOYEEID", 1001);
            List<Object> attributes = new ArrayList();
            attributes.add("EMPLOYEEID");
            attributes.add("NAME");
            attributes.add("EMAIL");

            List<Object> orderBy = new ArrayList();
            orderBy.add("NAME");
            AdvancedEntityResult eResult = iemployeeService.employeePaginationQuery(keysValues, attributes, 3, 0, orderBy);

            assertEquals(1, eResult.calculateRecordNumber());

            // Get the first value
            assertEquals("Vinod", eResult.getRecordValues(0).get("NAME"));
        }

        @Disabled
        @Test
        void when_employeeInsert_receive_attributes_expected_EntityResult() {


            Map<String, Object> attributesValues = new HashMap();
            attributesValues.put("EMPLOYEEID", 1004);
            attributesValues.put("NAME", "Elsa");
            attributesValues.put("EMAIL", "elsa@imatia.com");
            attributesValues.put("OFFICEID", 0001);

            iemployeeService.employeeInsert(attributesValues);

            Map<String, Object> keysValues = new HashMap();
            keysValues.put("NAME", "Elsa");

            List<String> attributesList = new ArrayList();
            attributesList.add("EMPLOYEEID");
            attributesList.add("NAME");
            attributesList.add("EMAIL");
            attributesList.add("EMPLOYEEID");

            EntityResult eResult = iemployeeService.employeeQuery(keysValues, attributesList);

            assertEquals(1, eResult.getRecordValues(0).get("EMAIL"));
        }


        @Test
        void when_employeeUpdate_receive_attributes_and_keysValues_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap();
            keysValues.put("EMPLOYEEID", 1002);

            Map<String, Object> attributes = new HashMap();
            attributes.put("NAME", "Coincidir");

            EntityResult result = iemployeeService.employeeUpdate(attributes, keysValues);

            /*assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);*/

            assertEquals("Coincidir", result.getRecordValues(0).get("NAME"));

            // assertEquals(1001, recordValues.get("EMPLOYEEID"));


        }
    }


}
