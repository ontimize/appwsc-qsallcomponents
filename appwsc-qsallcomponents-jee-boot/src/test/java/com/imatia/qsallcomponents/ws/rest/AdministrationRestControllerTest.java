package com.imatia.qsallcomponents.ws.rest;

import com.imatia.qsallcomponents.api.services.IAdministrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
class AdministrationRestControllerTest {

    @InjectMocks
    AdministrationRestController administrationRestController;

    @Mock
    IAdministrationService iAdministrationService;


    @Nested
    class GetService {
        @Test
        void when_getService_expected_iAdministrationService() {
            IAdministrationService service = administrationRestController.getService();
            Assertions.assertEquals(iAdministrationService, service);
        }
    }

    @Nested
    class Tested {
        @Test
        void when_test_expected_ResponseEntity() {
            String who = "who";
            ResponseEntity<com.imatia.qsallcomponents.api.constants.entities.Test> testResponseEntity = administrationRestController.test(who);
            Assertions.assertEquals("<200 OK OK,[]>", testResponseEntity.toString());
        }
    }

    @Nested
    class Permissions {
        @Test
        void when_permissions_expected_ResponseEntity() {
            ResponseEntity<String> permissions = administrationRestController.permissions();
            Assertions.assertEquals("<200 OK OK,[]>", permissions.toString());
        }
    }

}