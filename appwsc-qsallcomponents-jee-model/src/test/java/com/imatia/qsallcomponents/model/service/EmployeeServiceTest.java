package com.imatia.qsallcomponents.model.service;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.db.AdvancedEntityResultMapImpl;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    DefaultOntimizeDaoHelper daoHelper;



    @Nested
    class Employees{

        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
        ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);
        @Test
        void when_employeeQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {}


        @Test
        void when_employeePaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvancedEntityResult(){
            keysValues.put("EMPLOYEEPHOTO", 1);
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            ArgumentCaptor<Integer> rNumber = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Integer> sIndex = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<List<String>> oBy = ArgumentCaptor.forClass(List.class);

            AdvancedEntityResult advancedEResult = Mockito.mock(AdvancedEntityResultMapImpl.class);

            Mockito.doReturn(advancedEResult).when(daoHelper).paginationQuery(Mockito.any(), ksValues.capture(), attrs.capture(), rNumber.capture(), sIndex.capture(), oBy.capture(), Mockito.any());
            employeeService.employeePaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);


        }
        }



    }


