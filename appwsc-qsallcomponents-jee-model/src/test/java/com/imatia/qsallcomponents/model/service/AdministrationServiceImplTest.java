package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.model.dao.UserRoleDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdministrationServiceImplTest {

    @InjectMocks
    AdministrationServiceImpl administrationService;

    @Mock
    UserRoleDao userRoleDao;

    @Nested
    class UserRoleQuery {

        @Test
        void when_receive_keysValues_and_attributes_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>();
            keysValues.put("keysValues1", "value1");
            attributes.add("attribute1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("keysValues1", "value1");
            toRet.addRecord(record);

            Mockito.doReturn(toRet).when(userRoleDao).query(keysValues, attributes, null, UserRoleDao.ROLES_WITH_USER_QUERY);
            EntityResult entityResult = administrationService.userRoleQuery(keysValues, attributes);
            assertEquals(toRet.get(keysValues), entityResult.get(keysValues));
            assertEquals(toRet.get(attributes), entityResult.get(attributes));
            assertNotNull(entityResult);

        }
    }


    @Nested
    class GetServerPermissionsSql {
        @Test
        void when_getServerPermissionsSql_expected_a_string() {

            StringBuffer sql = new StringBuffer();
            String serverPermissionsSql = administrationService.getServerPermissionsSql();
            String string = "string";
            assertEquals(sql.toString(), serverPermissionsSql);
            assertNotEquals(string, serverPermissionsSql);
            assertNotNull(serverPermissionsSql);
        }
    }

    @Nested
    class TestNested{

        @Test
        void when_test_receive_string_expected_Test(){
            String who = "who";
            com.imatia.qsallcomponents.api.constants.entities.Test test = administrationService.test(who);
            assertEquals(who,test.getWho());
        }
    }

}