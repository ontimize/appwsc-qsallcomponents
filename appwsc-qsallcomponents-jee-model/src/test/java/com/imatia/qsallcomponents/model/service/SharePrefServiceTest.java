package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.api.services.ISharePrefService;
import com.imatia.qsallcomponents.model.dao.setup.OCSharePreferences;
import com.imatia.qsallcomponents.model.dao.setup.OCSharePreferencesTarget;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SharePrefServiceTest {

    @InjectMocks
    SharePrefService sharePrefService;

    @Mock
    OCSharePreferences sharePreferences;

    @Mock
    DefaultOntimizeDaoHelper daoHelper;


    @Nested
    class ShareCRUD {

        Map<String, Object> keysValuesMap = new HashMap<>();

        Map<String, Object> attributesMap = new HashMap<>();

        List<String> attributesList = new ArrayList<>(Arrays.asList("attributeList1"));


        @Test
        void when_shareQuery_receive_keysValues_and_attributes_expected_EntityResult() {

            keysValuesMap.put("keysvaluesMap1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributesMap1", "value1");
            record.put("keysvaluesMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(sharePreferences, keysValuesMap, attributesList, ISharePrefService.JOIN_QUERY);
            EntityResult entityResult = sharePrefService.shareQuery(keysValuesMap, attributesList);
            assertEquals(toRet, entityResult);
        }

        @Test
        void when_shareInsert_receive_attributes_expected_EntityResult() {

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributesMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).insert(sharePreferences, attributesMap);
            EntityResult entityResult = sharePrefService.shareInsert(attributesMap);
            assertEquals(toRet, entityResult);
        }


        @Test
        void when_shareUpdate_receive_attributes_and_keyValues_and_expected_EntityResult() {

            attributesMap.put("attributesMap1", "value1");
            keysValuesMap.put("keysvaluesMap1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributesMap1", "value1");
            record.put("keysvaluesMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).update(sharePreferences, attributesMap, keysValuesMap);
            EntityResult entityResult = sharePrefService.shareUpdate(attributesMap, keysValuesMap);
            assertEquals(toRet, entityResult);

        }

        @Test
        void when_shareDelete_receive_keysValues_expected_EntityResult() {

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("keysValuesMap1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).delete(sharePreferences, keysValuesMap);
            EntityResult entityResult = sharePrefService.shareDelete(keysValuesMap);
            assertEquals(toRet, entityResult);

        }


    }


}