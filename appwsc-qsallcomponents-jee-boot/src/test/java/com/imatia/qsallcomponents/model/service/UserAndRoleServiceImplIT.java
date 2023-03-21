package com.imatia.qsallcomponents.model.service;


import com.imatia.qsallcomponents.api.constants.entities.RoleServerPermission;
import com.imatia.qsallcomponents.api.constants.entities.ServerPermission;
import com.imatia.qsallcomponents.api.constants.entities.User;
import com.imatia.qsallcomponents.api.services.IUserAndRoleService;
import com.imatia.qsallcomponents.model.dao.*;
import com.ontimize.jee.common.db.AdvancedEntityResult;
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
import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = {CustomerService.class,
        CustomerDao.class,
        CustomerTypeDao.class,
        CustomerAccountDao.class,
        UserAndRoleServiceImpl.class,
        UserDao.class,
        UserRoleDao.class,
        RoleDao.class,
        ServerRoleDao.class,
        DMSCreationHelper.class
})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserAndRoleServiceImplIT {

    @Autowired
    IUserAndRoleService iUserAndRoleService;

    @MockBean
    IDMSService dmsService;

    @Autowired
    ServerRoleDao serverRoleDao;

    @BeforeAll
    static void initDataBase(@Autowired DataSource dataSource) throws SQLException {

        Connection con = dataSource.getConnection();
        Statement statement = con.createStatement();


        statement.execute("CREATE TABLE TUSER(USER_ VARCHAR(50) NOT NULL PRIMARY KEY," +
                "PASSWORD VARCHAR(50)," +
                "NAME VARCHAR(50)," +
                "SURNAME VARCHAR(50)," +
                "EMAIL VARCHAR(50)," +
                "NIF VARCHAR(50)," +
                "USERBLOCKED TIMESTAMP," +
                "LASTPASSWORDUPDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FIRSTLOGIN BOOLEAN DEFAULT TRUE," +
                "DOWN_DATE TIMESTAMP)");
        statement.executeUpdate("INSERT INTO TUSER VALUES('block','demouser','User blocked','Bloqueo','User Bloked',NULL,'2016-09-19 12:00:00.000000','2016-03-02 14:59:00.450000',FALSE,NULL)");
        statement.executeUpdate("INSERT INTO TUSER VALUES('demo','demouser','demo','demo',NULL,'44460713B',NULL,NULL,NULL,NULL)");
        statement.executeUpdate("INSERT INTO TUSER VALUES('democif','xYlJRMBKJs06a8c5tSjQn0eEju8=','democif','democif','democif',NULL,NULL,'2015-12-01 00:00:00.000000',FALSE,NULL)");


        statement.execute("CREATE TABLE TROLE(ID_ROLENAME INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY," +
                "ROLENAME VARCHAR(255)," +
                "XMLCLIENTPERMISSION VARCHAR(16777216))");
        statement.executeUpdate("INSERT INTO TROLE VALUES(0,'admin','<?xml version=\"1.0\" encoding=\"UTF-8\"?><security></security>')");
        statement.executeUpdate("INSERT INTO TROLE VALUES(1,'user','<?xml version=\"1.0\" encoding=\"UTF-8\"?><security></security>')");


        statement.execute("CREATE TABLE TUSER_ROLE(ID_USER_ROLE INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY," +
                "ID_ROLENAME INTEGER," +
                "USER_ VARCHAR(50))");
        statement.executeUpdate("INSERT INTO TUSER_ROLE VALUES(0,0,'demo')");
        statement.executeUpdate("INSERT INTO TUSER_ROLE VALUES(1,0,'block')");
        statement.executeUpdate("INSERT INTO TUSER_ROLE VALUES(2,0,'pablo.martinez')");

        statement.execute("CREATE TABLE TROLE_SERVER_PERMISSION(ID_ROLE_SERVER_PERMISSION INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY," +
                "ID_ROLENAME INTEGER," +
                "ID_SERVER_PERMISSION INTEGER)");
        statement.executeUpdate("INSERT INTO TROLE_SERVER_PERMISSION VALUES(0,0,0)");
        statement.executeUpdate("INSERT INTO TROLE_SERVER_PERMISSION VALUES(1,0,1)");
        statement.executeUpdate("INSERT INTO TROLE_SERVER_PERMISSION VALUES(2,0,2)");


        statement.execute("CREATE TABLE TSERVER_PERMISSION(ID_SERVER_PERMISSION INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY," +
                "PERMISSION_NAME VARCHAR(16777216))");

        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(0,'com.ontimize.jee.server.services.dms.DMSServiceImpl/fileGetContentOfVersion')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(1,'com.ontimize.jee.server.services.dms.DMSServiceImpl/documentGetProperty')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(2,'com.ontimize.jee.server.services.dms.DMSServiceImpl/fileRecoverPreviousVersion')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(3,'com.ontimize.jee.server.services.dms.DMSServiceImpl/documentDeleteProperties')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(4,'com.ontimize.jee.server.services.dms.DMSServiceImpl/documentGetProperties')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(5,'com.ontimize.jee.server.services.dms.DMSServiceImpl/documentGetAllFiles')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(6,'com.ontimize.jee.server.services.dms.DMSServiceImpl/setRelatedDocuments')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(7,'com.ontimize.jee.server.services.dms.DMSServiceImpl/documentAddProperties')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(8,'com.ontimize.jee.server.services.dms.DMSServiceImpl/getRelatedDocument')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(9,'com.ontimize.jee.server.services.dms.DMSServiceImpl/categoryGetForDocument')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(10,'com.ontimize.jee.server.services.dms.DMSServiceImpl/moveFilesToCategory')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(11,'com.ontimize.jee.server.services.dms.DMSServiceImpl/fileVersionQuery')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(12,'com.ontimize.jee.server.services.dms.DMSServiceImpl/documentQuery')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(13,'com.ontimize.jee.server.services.dms.DMSServiceImpl/documentInsert')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(14,'com.ontimize.jee.server.services.dms.DMSServiceImpl/documentUpdate')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(15,'com.ontimize.jee.server.services.dms.DMSServiceImpl/documentGetFiles')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(16,'com.ontimize.jee.server.services.dms.DMSServiceImpl/categoryInsert')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(17,'com.ontimize.jee.server.services.dms.DMSServiceImpl/fileInsert')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(18,'com.ontimize.jee.server.services.dms.DMSServiceImpl/categoryUpdate')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(19,'com.ontimize.jee.server.services.dms.DMSServiceImpl/categoryDelete')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(20,'com.ontimize.jee.server.services.dms.DMSServiceImpl/fileDelete')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(21,'com.ontimize.jee.server.services.dms.DMSServiceImpl/fileGetVersions')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(22,'com.ontimize.jee.server.services.dms.DMSServiceImpl/fileGetContent')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(23,'com.ontimize.jee.server.services.dms.DMSServiceImpl/fileUpdate')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(24,'com.ontimize.jee.server.services.dms.DMSServiceImpl/documentDelete')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(25,'com.ontimize.jee.server.services.dms.DMSServiceImpl/fileQuery')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(26,'com.ontimize.jee.common.services.dms.IDMSService/categoryGetForDocument')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(27,'com.ontimize.jee.common.services.dms.IDMSService/moveFilesToCategory')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(28,'com.ontimize.jee.common.services.dms.IDMSService/fileVersionQuery')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(29,'com.ontimize.jee.common.services.dms.IDMSService/documentQuery')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(30,'com.ontimize.jee.common.services.dms.IDMSService/documentInsert')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(31,'com.ontimize.jee.common.services.dms.IDMSService/documentUpdate')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(32,'com.ontimize.jee.common.services.dms.IDMSService/documentGetFiles')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(33,'com.ontimize.jee.common.services.dms.IDMSService/categoryInsert')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(34,'com.ontimize.jee.common.services.dms.IDMSService/fileInsert')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(35,'com.ontimize.jee.common.services.dms.IDMSService/categoryUpdate')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(36,'com.ontimize.jee.common.services.dms.IDMSService/categoryDelete')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(37,'com.ontimize.jee.common.services.dms.IDMSService/fileDelete')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(38,'com.ontimize.jee.common.services.dms.IDMSService/fileGetVersions')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(39,'com.ontimize.jee.common.services.dms.IDMSService/fileGetContent')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(40,'com.ontimize.jee.common.services.dms.IDMSService/fileUpdate')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(41,'com.ontimize.jee.common.services.dms.IDMSService/documentDelete')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(42,'com.ontimize.jee.common.services.dms.IDMSService/fileQuery')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(43,'com.ontimize.jee.common.services.formprovider.IFormProviderService/getXMLForm')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(44,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/getUserList')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(45,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/addSharedItem')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(46,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/editTargetSharedElement')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(47,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/getSharedItemsWithUser')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(48,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/getSharedItemsWithUserAndKey')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(49,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/getTargetSharedElementMenuList')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(50,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/getTargetSharedItemsList')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(51,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/getSourceSharedItemsList')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(52,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/getSourceSharedElementMenuList')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(53,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/getSharedElementMessage')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(54,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/getSharedElementValue')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(55,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/countSharedItemByNameAndUser')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(56,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/getSharedItem')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(57,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/getTargetSharedItem')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(58,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/deleteSharedItem')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(59,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/deleteTargetSharedItem')");
        statement.executeUpdate("INSERT INTO TSERVER_PERMISSION VALUES(60,'com.ontimize.jee.common.services.sharepreferences.ISharePreferencesService/updateSharedItem')");


    }


    @AfterAll
    static void tearDown(@Autowired DataSource dataSource) throws SQLException {

        Connection con = dataSource.getConnection();
        Statement statement = con.createStatement();

        statement.executeUpdate("DROP TABLE TUSER");
        statement.executeUpdate("DROP TABLE TROLE");
        statement.executeUpdate("DROP TABLE TUSER_ROLE");
        statement.executeUpdate("DROP TABLE TROLE_SERVER_PERMISSION");
        statement.executeUpdate("DROP TABLE TSERVER_PERMISSION");

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

            EntityResult result = iUserAndRoleService.userQuery(keysValues, attributes);
            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("block", recordValues.get("USER_"));
            assertEquals("demouser", recordValues.get("PASSWORD"));

        }

        @Test
        void when_userPaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvancedEntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("USER_", "block");

            List<String> attributes = new ArrayList<>();
            attributes.add("USER_");
            attributes.add("PASSWORD");
            attributes.add("NAME");
            attributes.add("SURNAME");
            attributes.add("EMAIL");
            attributes.add("NIF");

            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            AdvancedEntityResult result = iUserAndRoleService.userPaginationQuery(keysValues, attributes, 3, 0, orderBy);
            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("block", recordValues.get("USER_"));
            assertEquals("demouser", recordValues.get("PASSWORD"));

        }

        @Test
        void when_userInsert_receive_keysValues_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("USER_", "insert");
            keysValues.put("PASSWORD", "password");
            keysValues.put("NAME", "name");
            keysValues.put("SURNAME", "surname");
            keysValues.put("EMAIL", "correo");
            keysValues.put("NIF", "36363636T");

            iUserAndRoleService.userInsert(keysValues);

            List<String> attributes = new ArrayList<>();
            attributes.add("USER_");
            attributes.add("PASSWORD");
            attributes.add("NAME");
            attributes.add("SURNAME");
            attributes.add("EMAIL");
            attributes.add("NIF");

            EntityResult result = iUserAndRoleService.userQuery(keysValues, attributes);
            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("insert", recordValues.get("USER_"));
            assertEquals("password", recordValues.get("PASSWORD"));


        }

        @Test
        void when_userUpdate_receive_attributesValues_and_keyValues_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap();
            keysValues.put("USER_", "democif");

            Map<String, Object> attributesValues = new HashMap();
            attributesValues.put("PASSWORD", "Update");

            iUserAndRoleService.userUpdate(attributesValues, keysValues);

            List<String> attributes = new ArrayList<>();
            attributes.add("USER_");
            attributes.add("PASSWORD");

            EntityResult result = iUserAndRoleService.userQuery(keysValues, attributes);

            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("democif", recordValues.get("USER_"));
            assertEquals("Update", recordValues.get("PASSWORD"));

        }


        @Test
        void when_userDelete_receive_keysValues_expected_EntityResult() {

            Map<String, Object> keysValues = new HashMap();
            keysValues.put("USER_", "democif");

            iUserAndRoleService.userDelete(keysValues);

            List<String> attributes = new ArrayList<>();
            attributes.add("USER_");
            attributes.add("PASSWORD");
            attributes.add("NAME");
            attributes.add("SURNAME");
            attributes.add("EMAIL");
            attributes.add("NIF");
            attributes.add("DOWN_DATE");

            EntityResult result = iUserAndRoleService.userQuery(keysValues, attributes);
            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("democif", recordValues.get("USER_"));
            assertNotNull("DOWN_DATE");

        }
    }

    @Nested
    class RoleCRUD {

        @Test
        void when_roleQuery_receive_keysValues_and_attributes_expected_EntityResult() {

            Map<String, Object> keysValues = new HashMap();
            keysValues.put("ID_ROLENAME", 0);

            List<String> attributes = new ArrayList<>();
            attributes.add("ID_ROLENAME");
            attributes.add("ROLENAME");

            EntityResult result = iUserAndRoleService.roleQuery(keysValues, attributes);
            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals(0, recordValues.get("ID_ROLENAME"));
            assertEquals("admin", recordValues.get("ROLENAME"));
        }

        @Test
        void when_roleInsert_receive_keysValues_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap();
            keysValues.put("ID_ROLENAME", 2);
            keysValues.put("ROLENAME", "insert");

            iUserAndRoleService.roleInsert(keysValues);

            List<String> attributes = new ArrayList<>();
            attributes.add("ID_ROLENAME");
            attributes.add("ROLENAME");

            EntityResult result = iUserAndRoleService.roleQuery(keysValues, attributes);

            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals(2, recordValues.get("ID_ROLENAME"));
            assertEquals("insert", recordValues.get("ROLENAME"));
        }

        @Test
        void when_roleUpdate_receive_attributes_and_keyValues_expected_EntityResult() {

            Map<String, Object> keysValues = new HashMap();
            keysValues.put("ID_ROLENAME", 1);

            Map<String, Object> attributesValues = new HashMap();
            attributesValues.put("ROLENAME", "Update");

            iUserAndRoleService.roleUpdate(attributesValues, keysValues);

            List<String> attributes = new ArrayList<>();
            attributes.add("ID_ROLENAME");
            attributes.add("ROLENAME");

            EntityResult result = iUserAndRoleService.roleQuery(keysValues, attributes);

            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals(1, recordValues.get("ID_ROLENAME"));
            assertEquals("Update", recordValues.get("ROLENAME"));

        }

        @Test
        void when_roleDelete_receive_keyValues_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap();
            keysValues.put("ID_ROLENAME", 1);

            serverRoleDao.unsafeDelete(keysValues);

            Map<String, Object> attributesValues = new HashMap();
            attributesValues.put("ROLENAME", "Update");

            iUserAndRoleService.roleDelete(keysValues);

            List<String> attributes = new ArrayList<>();
            attributes.add("ID_ROLENAME");
            attributes.add("ROLENAME");

            EntityResult result = iUserAndRoleService.roleQuery(keysValues, attributes);
            assertNull(result.getRecordValues(0).get("USER_"));

        }
    }

    @Nested
    class ServerRole {


        @Test
        void when_serverRoleQuery_receive_keysValues_and_attributes_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap();
            keysValues.put("ID_ROLE_SERVER_PERMISSION", 0);
            keysValues.put("ID_ROLENAME", 0);

            List<String> attributes = new ArrayList<>();
            attributes.add("ID_ROLE_SERVER_PERMISSION");
            attributes.add("ID_ROLENAME");
            attributes.add("ID_SERVER_PERMISSION");

            EntityResult result = iUserAndRoleService.serverRoleQuery(keysValues, attributes);

            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals(0, recordValues.get("ID_ROLE_SERVER_PERMISSION"));
            assertEquals(0, recordValues.get("ID_ROLENAME"));

        }

        /*@Test
        void when_serverRoleUpdate_receive_attributesValues_and_keysValues_expected_insert() {
            Map<String, Object> keysValues = new HashMap();
            keysValues.put(RoleServerPermission.ACTIVED, "S");
            keysValues.put("ID_SERVER_PERMISSION", 2);
            keysValues.put("ID_ROLENAME", 0);*/

             /*
            Al ponerle keysValues.put(RoleServerPermission.ACTIVED, "S"); para poder testear la parte del Insert,
            falla porque le inserta [] a: "CASE WHEN tur.ID_ROLENAME IS NOT NULL THEN 'S' ELSE 'N' END"
            al detectar los espacios como caracteres conflictivos
            la querie sin los braquets funciona correctamente /
             */

           /* Map<String, Object> attributesValues = new HashMap();
            attributesValues.put("ID_ROLE_SERVER_PERMISSION", 3);
            attributesValues.put("ID_ROLENAME", 0);
            attributesValues.put("ID_SERVER_PERMISSION", 2);

            iUserAndRoleService.serverRoleUpdate(attributesValues, keysValues);

            List<String> attributes = new ArrayList<>();
            attributes.add("PERMISSION_NAME");
            attributes.add("ID_SERVER_PERMISSION");

            EntityResult result = iUserAndRoleService.serverRoleQuery(keysValues, attributes);
            assertEquals(2, result.get("ID_SERVER_PERMISSION"));

        }*/

        @Test
        void when_serverRoleUpdate_receive_attributesValues_and_keysValues_expected_EntityResult_delete() {
            Map<String, Object> keysValues = new HashMap();
            keysValues.put("ID_SERVER_PERMISSION", 2);
            keysValues.put("ID_ROLENAME", 0);

            Map<String, Object> attributesValues = new HashMap();
            attributesValues.put("ID_ROLE_SERVER_PERMISSION", 3);
            attributesValues.put("ID_ROLENAME", 0);
            attributesValues.put("ID_SERVER_PERMISSION", 2);

            iUserAndRoleService.serverRoleUpdate(attributesValues, keysValues);

            List<String> attributes = new ArrayList<>();
            attributes.add("ID_SERVER_PERMISSION");

            EntityResult result = iUserAndRoleService.serverRoleQuery(keysValues, attributes);
            assertNull(result.getRecordValues(0).get("ID_ROLE_SERVER_PERMISSION"));
        }
    }


    @Nested
    class RoleForUserCRUD {


        @Test
        void when_rolesForUserQuery_receive_keysValues_and_attributes_expected_EntityResult_UserRoleDao_ROLES_WITH_USER_QUERY() {

            Map<String, Object> keysValues = new HashMap();
            keysValues.put("USER_", "block");

            List<String> attributes = new ArrayList<>();
            attributes.add("USER_");
            attributes.add("PASSWORD");
            attributes.add("NAME");
            attributes.add("SURNAME");
            attributes.add("EMAIL");
            attributes.add("NIF");
            attributes.add("USERBLOCKED");
            attributes.add("LASTPASSWORDUPDATE");
            attributes.add("FIRSTLOGIN");

            EntityResult result = iUserAndRoleService.rolesForUserQuery(keysValues, attributes);
            assertEquals(2, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("block", recordValues.get("USER_"));
            assertEquals("demouser", recordValues.get("PASSWORD"));

        }

        @Test
        void when_rolesForUserQuery_receive_keysValues_and_attributes_expected_EntityResult_UserRoleDao_ROLES_WITHOUT_USER_QUERY() {

            Map<String, Object> keysValues = new HashMap();
            keysValues.put("ID_ROLENAME", 0);

            List<String> attributes = new ArrayList<>();
            attributes.add("ID_ROLENAME");
            attributes.add("ROLENAME");
            attributes.add("XMLCLIENTPERMISSION");

            EntityResult result = iUserAndRoleService.rolesForUserQuery(keysValues, attributes);
            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals(0, recordValues.get("ID_ROLENAME"));
        }


        @Test
        void when_rolesForUserUpdate_receive_attributesValues_and_keysValues_expected_null() {

            Map<String, Object> keysValues = new HashMap();
            keysValues.put("USER_", "demo2");

            Map<String, Object> attributes = new HashMap();
            attributes.put("USER_", "demo2");
            attributes.put("PASSWORD", "demo2");
            attributes.put("NAME", "demo2");
            attributes.put("SURNAME", "demo2");
            attributes.put("EMAIL", "demo2");
            attributes.put("NIF", "demo2");

            EntityResult result = iUserAndRoleService.rolesForUserUpdate(keysValues, attributes);
            assertNull(result);

        }

        @Test
        void when_rolesForUserUpdate_receive_attributesValues_and_keysValues_expected_EntityResult_insert() {
            Map<String, Object> keysValues = new HashMap();
            keysValues.put(RoleServerPermission.ACTIVED, "S");
            keysValues.put("USER_", "demo2");
            keysValues.put("ID_ROLENAME", 0);


            Map<String, Object> attributes = new HashMap();
            attributes.put(ServerPermission.ID_SERVER_PERMISSION, "ID_SERVER_PERMISSION");
            attributes.put("USER_", "demo2");
            attributes.put("ID_ROLENAME", 0);
            attributes.put("PASSWORD", "demo2");
            attributes.put("NAME", "demo2");
            attributes.put("SURNAME", "demo2");
            attributes.put("EMAIL", "demo2");
            attributes.put("NIF", "demo2");

            EntityResult result = iUserAndRoleService.rolesForUserUpdate(keysValues, attributes);

            assertEquals(3, result.get("ID_USER_ROLE"));
        }

    }

    @Nested
    class SearchUsersCRUD {

        @Test
        void when_searchUsersQuery_receive_keysValues_and_attributes_expected_EntityResult_put_User_ID_USER() {
            Map<String, Object> keysValues = new HashMap();
            keysValues.put("USER_", "block");

            List<String> attributes = new ArrayList<>();
            attributes.add("USER_");
            attributes.add("PASSWORD");
            attributes.add("NAME");
            attributes.add("SURNAME");
            attributes.add("EMAIL");
            attributes.add("NIF");
            attributes.add("USERBLOCKED");
            attributes.add("LASTPASSWORDUPDATE");
            attributes.add("FIRSTLOGIN");

            EntityResult result = iUserAndRoleService.searchUsersQuery(keysValues, attributes);
            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("block", recordValues.get("USER_"));
            assertEquals("demouser", recordValues.get("PASSWORD"));

        }

        @Test
        void when_searchUsersUpdate_receive_attributes_and_keyValues_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap();
            keysValues.put("USER_", "democif");

            Map<String, Object> attributesValues = new HashMap();
            attributesValues.put("PASSWORD", "Update");

            iUserAndRoleService.searchUsersUpdate(attributesValues, keysValues);

            List<String> attributes = new ArrayList<>();
            attributes.add("USER_");
            attributes.add("PASSWORD");

            EntityResult result = iUserAndRoleService.searchUsersQuery(keysValues, attributes);

            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("democif", recordValues.get("USER_"));
            assertEquals("Update", recordValues.get("PASSWORD"));
        }

        @Test
        void when_searchUsersDelete_receive_keysValues_expected_EntityResult() {

            Map<String, Object> keysValuesMap = new HashMap<String, Object>();
            keysValuesMap.put("USER_", "democif");
            keysValuesMap.put(User.DOWN_DATE, new Date());

            iUserAndRoleService.searchUsersDelete(keysValuesMap);
            List<String> attributes = new ArrayList<>();
            attributes.add("USER_");
            attributes.add("PASSWORD");

            EntityResult result = iUserAndRoleService.searchUsersQuery(keysValuesMap, attributes);
            assertEquals(1, result.calculateRecordNumber());
            Map recordValues = result.getRecordValues(0);
            assertEquals("democif", recordValues.get("USER_"));
            assertEquals("Update", recordValues.get("PASSWORD"));


        }

    }


}
