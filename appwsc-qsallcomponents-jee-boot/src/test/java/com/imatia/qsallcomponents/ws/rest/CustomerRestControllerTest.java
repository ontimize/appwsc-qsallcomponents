package com.imatia.qsallcomponents.ws.rest;

import com.imatia.qsallcomponents.api.services.ICustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerRestControllerTest {

    @InjectMocks
    CustomerRestController customerRestController;

    @Mock
    ICustomerService customerService;

    @Nested
    class GetService{

        @Test
        void when_getService_expected_ICustomerService(){
            ICustomerService service = customerRestController.getService();
            Assertions.assertEquals(customerService, service);

        }

    }
}