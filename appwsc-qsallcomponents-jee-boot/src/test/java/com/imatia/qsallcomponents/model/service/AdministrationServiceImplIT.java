package com.imatia.qsallcomponents.model.service;


import com.imatia.qsallcomponents.api.services.IAdministrationService;
import com.imatia.qsallcomponents.model.dao.EmployeeDao;
import com.imatia.qsallcomponents.model.dao.EmployeeTypeDao;
import com.imatia.qsallcomponents.model.dao.UserRoleDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdministrationServiceImplIT {

    @Autowired
    IAdministrationService iAdministrationService;

    @Autowired
    UserRoleDao userRoleDao;

    @Nested
    class UserRoleQuery {

        @Test
        void when_receive_keysValues_and_attributes_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("keysValues1", "value1");

            List<String> attributes = new ArrayList<>();
            attributes.add(UserRoleDao.ROLES_WITH_USER_QUERY);


            EntityResult result = iAdministrationService.userRoleQuery(keysValues, attributes);
            assertEquals(1, result.calculateRecordNumber());

            assertNotNull(result);

        }
    }


}
