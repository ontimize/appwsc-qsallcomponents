package com.imatia.qsallcomponents.model.service;


import com.imatia.qsallcomponents.api.services.ICustomerService;
import com.imatia.qsallcomponents.model.dao.CustomerAccountDao;
import com.imatia.qsallcomponents.model.dao.CustomerDao;
import com.imatia.qsallcomponents.model.dao.CustomerTypeDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.services.dms.IDMSService;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import com.ontimize.jee.server.services.dms.DMSCreationHelper;
import com.ontimize.jee.server.services.dms.DMSServiceFileHelper;
import com.ontimize.jee.server.services.dms.dao.IDMSDocumentFileVersionDao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@SpringBootTest(classes = {
        CustomerDao.class,
        CustomerService.class,
        CustomerAccountDao.class,
        CustomerTypeDao.class,
        IDMSService.class,
        DMSServiceFileHelper.class
})
@ExtendWith(SpringExtension.class)
@ActiveProfiles({"test-dms"})
@EnableAutoConfiguration
public class CustomerServiceIT {

    @Autowired
    ICustomerService iCustomerService;

    @Autowired
    DataSource dataSource;

    @Autowired
    IDMSDocumentFileVersionDao idmsDocumentFileVersionDao;
    @Autowired
    CustomerDao customerDao;

    @Autowired
    CustomerTypeDao customerTypeDao;

    @Autowired
    CustomerAccountDao customerAccountDao;
    @Autowired
    DefaultOntimizeDaoHelper daoHelper;

    @Autowired
    DMSCreationHelper dmsHelper;


    @BeforeAll
    static void initDataBase(@Autowired DataSource dataSource) throws SQLException {

        Connection con = dataSource.getConnection();
        Statement statement = con.createStatement();

        statement.execute("CREATE TABLE TDMS_DOC(ID_DMS_DOC INTEGER IDENTITY NOT NULL PRIMARY KEY,UPDATE_DATE TIMESTAMP,UPDATE_BY_ID INTEGER,DOC_NAME VARCHAR(255) NOT NULL,OWNER_ID INTEGER NOT NULL,DOC_DESCRIPTION CLOB(1G),DOC_KEYWORDS VARCHAR(255))");
        statement.execute("CREATE TABLE TDMS_DOC_FILE(ID_DMS_DOC_FILE INTEGER IDENTITY NOT NULL PRIMARY KEY,FILE_NAME VARCHAR(255) NOT NULL,ID_DMS_DOC INTEGER NOT NULL,FILE_TYPE VARCHAR(255),ID_DMS_DOC_CATEGORY INTEGER)");
        statement.execute("CREATE TABLE TDMS_DOC_FILE_VERSION(ID_DMS_DOC_FILE_VERSION INTEGER IDENTITY NOT NULL PRIMARY KEY,FILE_PATH VARCHAR(500),VERSION INTEGER NOT NULL,FILE_DESCRIPTION CLOB(1G),IS_ACTIVE CHARACTER(1) NOT NULL,FILE_ADDED_DATE TIMESTAMP NOT NULL,FILE_ADDED_USER_ID INTEGER NOT NULL,ID_DMS_DOC_FILE INTEGER NOT NULL,THUMBNAIL BLOB(1G),FILE_SIZE INTEGER)");
        statement.execute("CREATE TABLE TDMS_DOC_PROPERTY(ID_DMS_DOC_PROPERTY INTEGER IDENTITY NOT NULL PRIMARY KEY,DOC_PROPERTY_KEY VARCHAR(255) NOT NULL,DOC_PROPERTY_VALUE VARCHAR(255),ID_DMS_DOC INTEGER NOT NULL)");
        statement.execute("CREATE TABLE TDMS_RELATED_DOC(ID_DMS_RELATED_PROPERTY INTEGER IDENTITY NOT NULL PRIMARY KEY,ID_DMS_DOC_MASTER INTEGER NOT NULL,ID_DMS_DOC_CHILD INTEGER NOT NULL)");
        statement.execute("CREATE TABLE TDMS_DOC_CATEGORY(ID_DMS_DOC_CATEGORY INTEGER IDENTITY NOT NULL PRIMARY KEY,ID_DMS_DOC INTEGER NOT NULL,ID_DMS_DOC_CATEGORY_PARENT INTEGER,CATEGORY_NAME VARCHAR(255) NOT NULL)");

        statement.executeUpdate("ALTER TABLE TDMS_DOC_FILE ADD CONSTRAINT TDMS_DOC_FILE_FK FOREIGN KEY(ID_DMS_DOC) REFERENCES TDMS_DOC(ID_DMS_DOC)");
        statement.executeUpdate("ALTER TABLE TDMS_DOC_FILE_VERSION ADD CONSTRAINT TDMS_DOC_FILE_VERSION_FK FOREIGN KEY(ID_DMS_DOC_FILE) REFERENCES TDMS_DOC_FILE(ID_DMS_DOC_FILE");
        statement.executeUpdate("ALTER TABLE TDMS_DOC_PROPERTY ADD CONSTRAINT TDMS_DOC_PROPERTY_FK FOREIGN KEY(ID_DMS_DOC) REFERENCES TDMS_DOC(ID_DMS_DOC)");
        statement.executeUpdate("ALTER TABLE TDMS_RELATED_DOC ADD CONSTRAINT TDMS_RELATED_DOC_FK FOREIGN KEY(ID_DMS_DOC_MASTER) REFERENCES TDMS_DOC(ID_DMS_DOC)");
        statement.executeUpdate("ALTER TABLE TDMS_RELATED_DOC ADD CONSTRAINT TDMS_RELATED_DOC_FK_1 FOREIGN KEY(ID_DMS_DOC_CHILD) REFERENCES TDMS_DOC(ID_DMS_DOC)");
        statement.executeUpdate("ALTER TABLE TDMS_DOC_CATEGORY ADD CONSTRAINT TDMS_DOC_CATEGORY_FK FOREIGN KEY(ID_DMS_DOC) REFERENCES TDMS_DOC(ID_DMS_DOC)");
        statement.executeUpdate("ALTER TABLE TDMS_DOC_FILE ADD CONSTRAINT TDMS_DOC_FILE_FK_1 FOREIGN KEY(ID_DMS_DOC_CATEGORY) REFERENCES TDMS_DOC_CATEGORY(ID_DMS_DOC_CATEGORY)");

        statement.execute("CREATE TABLE TSERVER_PERMISSION(ID_SERVER_PERMISSION INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,PERMISSION_NAME VARCHAR(16777216))");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/fileGetContentOfVersion'))");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/documentGetProperty')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/fileRecoverPreviousVersion')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/documentDeleteProperties')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/documentGetProperties')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/documentGetAllFiles')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/setRelatedDocuments')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/documentAddProperties')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/getRelatedDocument')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/categoryGetForDocument')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/moveFilesToCategory')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/fileVersionQuery')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/documentQuery')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/documentInsert')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/documentUpdate')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/documentGetFiles')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/categoryInsert')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/fileInsert')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/categoryUpdate')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/categoryDelete')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/fileDelete')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/fileGetVersions')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/fileGetContent')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/fileUpdate')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/documentDelete')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION (PERMISSION_NAME) VALUES('com.ontimize.jee.server.services.dms.DMSServiceImpl/fileQuery')");


        statement.execute("CREATE TABLE CUSTOMERS(CUSTOMERID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY," +
                "CUSTOMERTYPEID INTEGER," +
                "NAME VARCHAR(255)," +
                "ADDRESS VARCHAR(255)," +
                "COMMENTS VARCHAR(16777216)," +
                "STARTDATE TIMESTAMP," +
                "PHOTO VARBINARY(16777216)," +
                "SURNAME VARCHAR(255)," +
                "ID VARCHAR(50)," +
                "EMAIL VARCHAR(255)," +
                "COMMENTS_EN_US VARCHAR(16777216)," +
                "COMMENTS_ES_ES VARCHAR(16777216)," +
                "COMMENTS_GL_ES VARCHAR(16777216)," +
                "LONGITUDE DOUBLE," +
                "LATITUDE DOUBLE," +
                "SIGNATURE VARBINARY(16777216)," +
                "ID_DMS_DOC INTEGER," +
                "COUNTRY VARCHAR(50)," +
                "STATE VARCHAR(50)," +
                "ZIPCODE VARCHAR(50)," +
                "PHONE VARCHAR(50))");
        statement.executeUpdate("INSERT INTO CUSTOMERS VALUES(7511,2,'Daisy','49 Lavender Gardens, Battersea, SW11 1DJ ',NULL,'2009-10-06 00:00:00.000000',NULL,'Lawrence','23459862T','Daisy.Lawrence@imatia.com'," +
                "NULL,NULL,NULL,-0.1624365E0,51.4642115E0,NULL,1,'United Kingdom','London',NULL,NULL)");
        statement.executeUpdate("INSERT INTO CUSTOMERS VALUES(10602,3,'James','13, Downing Street','Connected with capital surpluses.','2008-12-23 00:00:00.000000'," +
                "NULL,'Alan','99557548R','james.alan@alan.inc','Comments','Comenttarios para James Alan','Comments',-0.1271615E0,51.5032069E0,NULL,2,'United Kingdom','London',NULL,NULL)");
        statement.executeUpdate("INSERT INTO CUSTOMERS VALUES(19271,3,'Aubrey','Tidorestraat 58-128',NULL,'2010-07-15 00:00:00.000000',NULL,'Engels','75395182D','Aubrey.Engels@ontimize.com',NULL,NULL,NULL," +
                "4.9403621E0,52.3600487E0,NULL,17,'The Netherlands','Amsterdam','1095',NULL)");

        statement.executeUpdate("ALTER TABLE CUSTOMERS ADD ID_DMS_DOC INTEGER");
        statement.executeUpdate("ALTER TABLE CUSTOMERS ADD CONSTRAINT CUSTOMERS_FK FOREIGN KEY(ID_DMS_DOC) REFERENCES TDMS_DOC(ID_DMS_DOC)");


        statement.execute("CREATE TABLE CUSTOMERACCOUNTS(CUSTOMERACCOUNTID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,\n" +
                "CUSTOMERID INTEGER NOT NULL," +
                "CCOUNTID INTEGER NOT NULL," +
                "ISOWNER BOOLEAN)");

        statement.executeUpdate("INSERT INTO CUSTOMERACCOUNTS VALUES(1,10602,1,FALSE");
        statement.executeUpdate("INSERT INTO CUSTOMERACCOUNTS VALUES(2,10602,8,TRUE)");
        statement.executeUpdate("INSERT INTO CUSTOMERACCOUNTS VALUES(3,10602,11,TRUE");


        statement.execute("CREATE TABLE CUSTOMERTYPES(CUSTOMERTYPEID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY," +
                "DESCRIPTION VARCHAR(255)," +
                "DESCRIPTION_EN_US VARCHAR(255)," +
                "DESCRIPTION_ES_ES VARCHAR(255)," +
                "DESCRIPTION_GL_ES VARCHAR(255))");

        statement.executeUpdate("INSERT INTO CUSTOMERTYPES VALUES(1,'Normal','Normal','Normal','Normal')");
        statement.executeUpdate("INSERT INTO CUSTOMERTYPES VALUES(2,'VIP','VIP','VIP','VIP')");
        statement.executeUpdate("INSERT INTO CUSTOMERTYPES VALUES(3,'Other','Other','Otro','Outro')");

    }


    @AfterAll
    static void tearDown(@Autowired DataSource dataSource) throws SQLException {

        Connection con = dataSource.getConnection();
        Statement statement = con.createStatement();

        statement.executeUpdate("DROP TABLE CUSTOMERS");
        statement.executeUpdate("DROP TABLE CUSTOMERACCOUNTS");
        statement.executeUpdate("DROP TABLE CUSTOMERTYPES");

        statement.executeUpdate("DROP TABLE TSERVER_PERMISSION");
        statement.executeUpdate("DROP TABLE TDMS_DOC");
        statement.executeUpdate("DROP TABLE TDMS_DOC_FILE");
        statement.executeUpdate("TDMS_DOC_FILE_VERSION");
        statement.executeUpdate("TDMS_DOC_PROPERTY");
        statement.executeUpdate("TDMS_RELATED_DOC");
        statement.executeUpdate("TDMS_DOC_CATEGORY");

    }


    @Nested
    class CustomerCRUD {

        @Test
        void when_customerQuery_receive_keysValues_and_attributes_and_expected_EntityResult_with_BytesBlock() {

            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("CUSTOMERID", 7511);

            List<String> attributes = new ArrayList();
            attributes.add("CUSTOMERID");
            attributes.add("CUSTOMERTYPEID");
            attributes.add("NAME");
            attributes.add("PHOTO");
            attributes.add("ADDRESS");

            EntityResult result = iCustomerService.customerQuery(keysValues, attributes);
            Map recordValues = result.getRecordValues(0);

            assertEquals(7511, recordValues.get("EMPLOYEEID"));
            assertNotNull(result.get("PHOTO"));

        }
    }

}
