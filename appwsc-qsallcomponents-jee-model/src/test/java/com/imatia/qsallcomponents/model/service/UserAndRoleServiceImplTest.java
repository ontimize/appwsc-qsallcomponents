package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.api.constants.ApplicationConstants;
import com.imatia.qsallcomponents.api.constants.entities.*;
import com.imatia.qsallcomponents.model.dao.RoleDao;
import com.imatia.qsallcomponents.model.dao.ServerRoleDao;
import com.imatia.qsallcomponents.model.dao.UserDao;
import com.imatia.qsallcomponents.model.dao.UserRoleDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.db.AdvancedEntityResultMapImpl;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import com.ontimize.jee.server.security.SecurityTools;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class UserAndRoleServiceImplTest {


    @InjectMocks
    UserAndRoleServiceImpl userAndRoleService;

    @Mock
    UserDao userDao;

    @Mock
    UserRoleDao userRolesDao;

    @Mock
    RoleDao roleDao;

    @Mock
    ServerRoleDao serverRoleDao;

    @Mock
    DefaultOntimizeDaoHelper daoHelper;

    @Nested
    class UserCRUD {

        Map<String, Object> keysValuesMap = new HashMap<>();

        Map<String, Object> attributesMap = new HashMap<>();

        List<String> attributesList = new ArrayList<>(Arrays.asList("attributeList1"));

        @Test
        void when_userQuery_receive_keysValues_and_attributes_expected_EntityResult() {

            keysValuesMap.put("keysvaluesMap1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributesMap1", "value1");
            record.put("keysvaluesMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(userDao, keysValuesMap, attributesList);
            EntityResult entityResult = userAndRoleService.userQuery(keysValuesMap, attributesList);
            assertEquals(toRet, entityResult);

        }


        @Test
        void when_userPaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvancedEntityResult() {
            keysValuesMap.put("keysValuesMap1", "value1");
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            AdvancedEntityResult advancedEResult = new AdvancedEntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributesList1", "value1");
            record.put("keysValuesMap1", "value1");
            advancedEResult.addRecord(record);

            Mockito.doReturn(advancedEResult).when(daoHelper).paginationQuery(userDao, keysValuesMap, attributesList, recordNumber, startIndex, orderBy);
            AdvancedEntityResult advancedEntityResult = userAndRoleService.userPaginationQuery(keysValuesMap, attributesList, recordNumber, startIndex, orderBy);
            assertEquals(advancedEResult, advancedEntityResult);
        }


        @Test
        void when_userUpdate_receive_attributes_and_keyValues_expected_EntityResult() {

            attributesMap.put("attributesMap1", "value1");
            keysValuesMap.put("keysvaluesMap1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributesMap1", "value1");
            record.put("keysvaluesMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).update(userDao, attributesMap, keysValuesMap);
            EntityResult entityResult = userAndRoleService.userUpdate(attributesMap, keysValuesMap);
            SecurityTools.invalidateSecurityManager(daoHelper.getApplicationContext());
            assertEquals(toRet, entityResult);

        }


        @Test
        void when_userDelete_receive_keysValues_expected_EntityResult() {

            Map<String, Object> updateValues = new HashMap<String, Object>();
            updateValues.put(User.DOWN_DATE, new Date());

            EntityResult result = userAndRoleService.userUpdate(updateValues, keysValuesMap);

            EntityResult entityResult = userAndRoleService.userDelete(keysValuesMap);
            assertEquals(result, entityResult);
        }

        @Test
        void when_userInsert_receive_keysValues_expected_EntityResult() {

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("keysValuesMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).insert(userDao, keysValuesMap);
            EntityResult entityResult = userAndRoleService.userInsert(keysValuesMap);
            assertEquals(toRet, entityResult);

        }

    }

    @Nested
    class RoleCRUD {

        Map<String, Object> keysValuesMap = new HashMap<>();

        Map<String, Object> attributesMap = new HashMap<>();

        List<String> attributesList = new ArrayList<>(Arrays.asList("attributeList1"));

        @Test
        void when_roleQuery_receive_keysValues_and_attributes_expected_EntityResult() {

            keysValuesMap.put("keysvaluesMap1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributesMap1", "value1");
            record.put("keysvaluesMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(roleDao, keysValuesMap, attributesList);
            EntityResult entityResult = userAndRoleService.roleQuery(keysValuesMap, attributesList);
            assertEquals(toRet, entityResult);

        }


        @Test
        void when_roleUpdate_receive_attributes_and_keyValues_expected_EntityResult() {

            attributesMap.put("attributesMap1", "value1");
            keysValuesMap.put("keysvaluesMap1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributesMap1", "value1");
            record.put("keysvaluesMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).update(roleDao, attributesMap, keysValuesMap);
            EntityResult entityResult = userAndRoleService.roleUpdate(attributesMap, keysValuesMap);
            SecurityTools.invalidateSecurityManager(daoHelper.getApplicationContext());
            assertEquals(toRet, entityResult);

        }


        @Test
        void when_roleDelete_receive_keyValues_expected_EntityResult() {

            keysValuesMap.put("keysvaluesMap1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("keysvaluesMap1", "value1");
            toRet.addRecord(record);

            serverRoleDao.unsafeDelete(keysValuesMap);
            Mockito.doReturn(toRet).when(daoHelper).delete(roleDao, keysValuesMap);
            EntityResult entityResult = userAndRoleService.roleDelete(keysValuesMap);
            SecurityTools.invalidateSecurityManager(daoHelper.getApplicationContext());
            assertEquals(toRet, entityResult);

        }


        @Test
        void when_roleInsert_receive_keyValues_expected_EntityResult() {

            keysValuesMap.put("keysvaluesMap1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("keysvaluesMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).insert(roleDao, keysValuesMap);
            EntityResult entityResult = userAndRoleService.roleInsert(keysValuesMap);
            SecurityTools.invalidateSecurityManager(daoHelper.getApplicationContext());
            assertEquals(toRet, entityResult);

        }


    }


    @Nested
    class ServerRoleQuery {

        Map<String, Object> keysValuesMap = new HashMap<>();

        List<String> attributesList = new ArrayList<>(Arrays.asList("attributeList1"));

        @Test
        void when_serverRoleQuery_receive_keysValues_and_attributes_expected_EntityResult_with_ServerRoleDao_ID_SERVER_ROLE_ALL_QUERY() {

            keysValuesMap.put("keysValuesMap1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("keysvaluesMap1", "value1");
            toRet.addRecord(record);

            Mockito.doReturn(toRet).when(daoHelper).query(serverRoleDao, keysValuesMap, attributesList, ServerRoleDao.ID_SERVER_ROLE_ALL_QUERY);
            EntityResult entityResult = userAndRoleService.serverRoleQuery(keysValuesMap, attributesList);
            assertEquals(toRet, entityResult);

        }

        @Test
        void when_serverRoleQuery_receive_keysValues_and_attributes_expected_EntityResult_with_ServerRoleDao_ID_SERVER_ROLE_QUERY() {

            keysValuesMap.put(Role.ID_ROLE, "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("keysvaluesMap1", "value1");
            record.put(Role.ID_ROLE, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(serverRoleDao, keysValuesMap, attributesList, ServerRoleDao.ID_SERVER_ROLE_QUERY);
            EntityResult entityResult = userAndRoleService.serverRoleQuery(keysValuesMap, attributesList);
            assertEquals(toRet, entityResult);
        }


    }


    @Nested
    class ServerRoleUpdate {

        Map<String, Object> keysValuesMap = new HashMap<>();

        Map<String, Object> attributesValues = new HashMap<>();


        @Test
        void when_serverRoleUpdate_receive_attributesValues_and_keysValues_expected_null() {

            attributesValues.put(RoleServerPermission.ACTIVED, "ACTIVED");
            keysValuesMap.put(ServerPermission.ID_SERVER_PERMISSION, "ID_SERVER_PERMISSION");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put(RoleServerPermission.ACTIVED, "ACTIVED");
            record.put(ServerPermission.ID_SERVER_PERMISSION, "ID_SERVER_PERMISSION");
            toRet.addRecord(record);

            Map<String, Object> valuesToInsert = new HashMap<>();
            valuesToInsert.put(Role.ID_ROLE, keysValuesMap.get(Role.ID_ROLE));
            valuesToInsert.put(ServerPermission.ID_SERVER_PERMISSION, keysValuesMap.get(ServerPermission.ID_SERVER_PERMISSION));

            EntityResult entityResult = userAndRoleService.serverRoleUpdate(attributesValues, keysValuesMap);
            assertNull(entityResult);


        }

        @Test
        void when_serverRoleUpdate_receive_attributesValues_and_keysValues_expected_EntityResult_insert() {

            attributesValues.put(RoleServerPermission.ACTIVED, "S");
            keysValuesMap.put(ServerPermission.ID_SERVER_PERMISSION, "ID_SERVER_PERMISSION");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put(RoleServerPermission.ACTIVED, "S");
            record.put(ServerPermission.ID_SERVER_PERMISSION, "ID_SERVER_PERMISSION");
            toRet.addRecord(record);

            ApplicationConstants.S.equals(attributesValues.get(RoleServerPermission.ACTIVED));
            Map<String, Object> valuesToInsert = new HashMap<>();
            valuesToInsert.put(Role.ID_ROLE, keysValuesMap.get(Role.ID_ROLE));
            valuesToInsert.put(ServerPermission.ID_SERVER_PERMISSION, keysValuesMap.get(ServerPermission.ID_SERVER_PERMISSION));

            Mockito.doReturn(toRet).when(daoHelper).insert(serverRoleDao, valuesToInsert);
            EntityResult entityResult = userAndRoleService.serverRoleUpdate(attributesValues, keysValuesMap);
            assertEquals(toRet, entityResult);


        }

        @Test
        void when_serverRoleUpdate_receive_attributesValues_and_keysValues_expected_EntityResult_delete() {

            attributesValues.put(RoleServerPermission.ACTIVED, "ACTIVED");
            keysValuesMap.put(ServerPermission.ID_SERVER_PERMISSION, "ID_SERVER_PERMISSION");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put(RoleServerPermission.ACTIVED, "ACTIVED");
            record.put(ServerPermission.ID_SERVER_PERMISSION, "ID_SERVER_PERMISSION");
            toRet.addRecord(record);

            keysValuesMap.put(RoleServerPermission.ID_ROLE_SERVER_PERMISSION, "ID_ROLE_SERVER_PERMISSION");

            Map<String, Object> valuesToDelete = new HashMap<>();
            valuesToDelete.put(RoleServerPermission.ID_ROLE_SERVER_PERMISSION, keysValuesMap.get(RoleServerPermission.ID_ROLE_SERVER_PERMISSION));

            Mockito.doReturn(toRet).when(daoHelper).delete(serverRoleDao, valuesToDelete);
            EntityResult entityResult = userAndRoleService.serverRoleUpdate(attributesValues, keysValuesMap);
            assertEquals(toRet, entityResult);


        }
    }


    @Nested
    class RoleForUserQuery {

        Map<String, Object> keysValuesMap = new HashMap<>();

        List<String> attributesList = new ArrayList<>(Arrays.asList("attributeList1"));


        @Test
        void when_rolesForUserQuery_receive_keysValues_and_attributes_expected_EntityResult_UserRoleDao_ROLES_WITHOUT_USER_QUERY() {

            attributesList.add("ACTIVED");
            keysValuesMap.put(UserRoleDao.ROLES_WITHOUT_USER_QUERY, "rolesWithoutUser");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put(RoleServerPermission.ACTIVED, "ACTIVED");
            record.put(UserRoleDao.ROLES_WITHOUT_USER_QUERY, "rolesWithoutUser");
            toRet.addRecord(record);

            Mockito.doReturn(toRet).when(daoHelper).query(userRolesDao, keysValuesMap, attributesList, UserRoleDao.ROLES_WITHOUT_USER_QUERY);

            EntityResult entityResult = userAndRoleService.rolesForUserQuery(keysValuesMap, attributesList);
            assertEquals(toRet, entityResult);
        }

        @Test
        void when_rolesForUserQuery_receive_keysValues_and_attributes_expected_EntityResult_UserRoleDao_ROLES_WITH_USER_QUERY() {

            attributesList.add("ACTIVED");
            keysValuesMap.put(User.ID_USER, "rolesWithoutUser");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put(RoleServerPermission.ACTIVED, "ACTIVED");
            record.put(User.ID_USER, "rolesWithoutUser");
            toRet.addRecord(record);

            Mockito.doReturn(toRet).when(daoHelper).query(userRolesDao, keysValuesMap, attributesList, UserRoleDao.ROLES_WITH_USER_QUERY);

            EntityResult entityResult = userAndRoleService.rolesForUserQuery(keysValuesMap, attributesList);
            assertEquals(toRet, entityResult);

        }

    }

    @Nested
    class RolesForUserUpdate {

        Map<String, Object> keysValuesMap = new HashMap<>();

        Map<String, Object> attributesValues = new HashMap<>();


        @Test
        void when_rolesForUserUpdate_receive_attributesValues_and_keysValues_expected_null() {

            attributesValues.put(RoleServerPermission.ACTIVED, "ACTIVED");
            keysValuesMap.put(ServerPermission.ID_SERVER_PERMISSION, "ID_SERVER_PERMISSION");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put(RoleServerPermission.ACTIVED, "ACTIVED");
            record.put(ServerPermission.ID_SERVER_PERMISSION, "ID_SERVER_PERMISSION");
            toRet.addRecord(record);

            Map<String, Object> valuesToInsert = new HashMap<>();
            valuesToInsert.put(User.ID_USER, keysValuesMap.get(User.ID_USER));
            valuesToInsert.put(Role.ID_ROLE, keysValuesMap.get(Role.ID_ROLE));

            EntityResult entityResult = userAndRoleService.rolesForUserUpdate(attributesValues, keysValuesMap);
            assertNull(entityResult);


        }

        @Test
        void when_rolesForUserUpdate_receive_attributesValues_and_keysValues_expected_EntityResult_insert() {

            attributesValues.put(RoleServerPermission.ACTIVED, "S");
            keysValuesMap.put(ServerPermission.ID_SERVER_PERMISSION, "ID_SERVER_PERMISSION");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put(RoleServerPermission.ACTIVED, "S");
            record.put(ServerPermission.ID_SERVER_PERMISSION, "ID_SERVER_PERMISSION");
            toRet.addRecord(record);

            ApplicationConstants.S.equals(attributesValues.get(RoleServerPermission.ACTIVED));
            Map<String, Object> valuesToInsert = new HashMap<>();
            valuesToInsert.put(User.ID_USER, keysValuesMap.get(User.ID_USER));
            valuesToInsert.put(Role.ID_ROLE, keysValuesMap.get(Role.ID_ROLE));

            Mockito.doReturn(toRet).when(daoHelper).insert(userRolesDao, valuesToInsert);
            EntityResult entityResult = userAndRoleService.rolesForUserUpdate(attributesValues, keysValuesMap);
            assertEquals(toRet, entityResult);

        }

        @Test
        void when_rolesForUserUpdate_receive_attributesValues_and_keysValues_expected_EntityResult_delete() {

            attributesValues.put(RoleServerPermission.ACTIVED, "ACTIVED");
            keysValuesMap.put(UserRole.ID_USER_ROLE, "ID_USER_ROLE");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put(RoleServerPermission.ACTIVED, "ACTIVED");
            record.put(UserRole.ID_USER_ROLE, "ID_USER_ROLE");
            toRet.addRecord(record);

            Map<String, Object> valuesToDelete = new HashMap<>();
            valuesToDelete.put(UserRole.ID_USER_ROLE, keysValuesMap.get(UserRole.ID_USER_ROLE));

            Mockito.doReturn(toRet).when(daoHelper).delete(userRolesDao, valuesToDelete);
            EntityResult entityResult = userAndRoleService.rolesForUserUpdate(attributesValues, keysValuesMap);
            assertEquals(toRet, entityResult);


        }
    }


    @Nested
    class SearchUsersCRUD {

        Map<String, Object> keysValuesMap = new HashMap<>();

        List<String> attributesList = new ArrayList<>(Arrays.asList("attributeList1"));

        @Test
        void when_searchUsersQuery_receive_keysValues_and_attributes_expected_EntityResult_put_User_ID_USER() {

            attributesList.add(User.ID_USER);
            keysValuesMap.put("keysvaluesMap1", "value1");

            EntityResult res = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            Map record = new Hashtable<>();
            record.put(User.ID_USER, "USER_");
            record.put("keysvaluesMap1", "value1");
            res.addRecord(record);

            Mockito.doReturn(res).when(daoHelper).query(userRolesDao, keysValuesMap, attributesList, UserRoleDao.DEFAULT_QUERY);
            EntityResult entityResult = userAndRoleService.searchUsersQuery(keysValuesMap, attributesList);
            assertEquals(res.containsKey(User.ID_USER), entityResult.containsKey(User.ID_USER));

        }

        @Test
        void when_searchUsersQuery_receive_keysValues_and_attributes_expected_EntityResult_containsKey_User_ID_USER() {

            attributesList.add(User.ID_USER);
            keysValuesMap.put("keysvaluesMap1", "value1");

            EntityResult res = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            Map record = new Hashtable<>();
            record.put(User.ID_USER, "USER_");
            record.put("keysvaluesMap1", "value1");
            res.addRecord(record);

            Mockito.doReturn(res).when(daoHelper).query(userRolesDao, keysValuesMap, attributesList, UserRoleDao.DEFAULT_QUERY);
            EntityResult entityResult = userAndRoleService.searchUsersQuery(keysValuesMap, attributesList);
            assertEquals(res.containsKey(User.ID_USER), entityResult.containsKey(User.ID_USER));

        }


    }


    @Nested
    class SearchUsersUpdate {

        Map<String, Object> keysValuesMap = new HashMap<>();

        Map<String, Object> attributesMap = new HashMap<>();

        @Test
        void when_searchUsersUpdate_receive_attributes_and_keyValues_expected_EntityResult() {
            attributesMap.put("attributesMap1", "value1");
            keysValuesMap.put("keysvaluesMap1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributesMap1", "value1");
            record.put("keysvaluesMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).update(userDao, attributesMap, keysValuesMap);
            EntityResult entityResult = userAndRoleService.searchUsersUpdate(attributesMap, keysValuesMap);
            SecurityTools.invalidateSecurityManager(daoHelper.getApplicationContext());
            assertEquals(toRet, entityResult);

        }

    }


    @Nested
    class SearchUsersDelete {

        @Test
        void when_searchUsersDelete_receive_keysValues_expected_EntityResult() {

            Map<String, Object> keysValuesMap = new HashMap<String, Object>();
            keysValuesMap.put(User.DOWN_DATE, new Date());

            EntityResult result = userAndRoleService.userDelete(keysValuesMap);

            EntityResult entityResult = userAndRoleService.searchUsersDelete(keysValuesMap);
            assertEquals(result, entityResult);

        }

    }
}


