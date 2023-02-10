package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.api.constants.entities.User;
import com.imatia.qsallcomponents.model.dao.UserDao;
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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserDao userDao;

    @Mock
    DefaultOntimizeDaoHelper daoHelper;

    @Nested
    class UserCRUD {

        Map<String, Object> keyMap = new HashMap<>();

        Map<String, Object> attrMap = new HashMap<>();

        List<String> attrList = new ArrayList<>(Arrays.asList("attributeList1"));

        @Test
        void when_userQuery_receive_keyMap_and_attrList_expected_EntityResult() {

            keyMap.put("keyMap1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attrList1", "value1");
            record.put("keyMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(userDao, keyMap, attrList);
            EntityResult entityResult = userService.userQuery(keyMap, attrList);
            assertEquals(toRet, entityResult);


        }


        @Test
        void when_userInsert_receive_attrMap_expected_EntityResult() {

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attrMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).insert(userDao, attrMap);
            EntityResult entityResult = userService.userInsert(attrMap);
            assertEquals(toRet, entityResult);

        }


        @Test
        void when_userUpdate_receive_attrMap_and_keyMapexpected_EntityResult() {

            attrMap.put("attrMap1", "value1");
            keyMap.put("keyMap1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attrMap1", "value1");
            record.put("keyMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).update(userDao, attrMap, keyMap);
            EntityResult entityResult = userService.userUpdate(attrMap, keyMap);
            assertEquals(toRet, entityResult);

        }


        @Test
        void when_userDelete_receive_keyMap_expected_EntityResult() {

            Map<Object, Object> attrMap = new HashMap<>();
            attrMap.put("user_down_date", new Timestamp(Calendar.getInstance().getTimeInMillis()));
            keyMap.put("keyMap1", "value1");

            daoHelper.update(userDao, attrMap, keyMap);
            userService.userDelete(keyMap);
            assertTrue(attrMap.containsKey("user_down_date"));
            assertNotNull(attrMap.get("user_down_date"));

        }



    }

}