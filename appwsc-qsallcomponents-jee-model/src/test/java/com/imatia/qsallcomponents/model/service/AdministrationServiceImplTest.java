package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.model.dao.UserRoleDao;
import com.ontimize.jee.common.dto.EntityResult;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
            keysValues.put("field1", "value1");
            attributes.add("attribute1");

            //tengo q validar q los parametros q recibe userRoleQuery son los mismos q devuelve el return

            ArgumentCaptor<Map<?, ?>> ksValues = ArgumentCaptor.forClass(Map.class);
            ArgumentCaptor<List<?>> attrs = ArgumentCaptor.forClass(List.class);
            String rolesWithUserQuery = UserRoleDao.ROLES_WITH_USER_QUERY;


            Mockito.verify(userRoleDao).query(ksValues.capture(), attrs.capture(), (List<?>) null, UserRoleDao.ROLES_WITH_USER_QUERY);
            EntityResult actual = administrationService.userRoleQuery(keysValues,attributes);

            String expected = "sqlTemplate";

            assertEquals(expected, actual);

        }
    }

}