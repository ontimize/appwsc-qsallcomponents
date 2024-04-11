package com.imatia.qsallcomponents.ws.rest;

import com.imatia.qsallcomponents.api.services.IUserAndRoleService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsersRestControllerTest {

    @InjectMocks
    UsersRestController usersRestController;

    @Mock
    IUserAndRoleService iUserAndRoleService;

    @Nested
    class GetService{

        @Test
        void when_getService_expected_IUserAndRoleService(){
            IUserAndRoleService service = usersRestController.getService();
            assertEquals(iUserAndRoleService,service);

        }
    }

    @Nested
    class Login{
        @Test
        void when_login_expected_ResponseEntity(){
            ResponseEntity<Void> login = usersRestController.login();
            assertEquals("<200 OK OK,[]>",login.toString());
        }
    }


}