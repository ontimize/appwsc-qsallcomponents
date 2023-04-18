package com.imatia.qsallcomponents.ws.rest;

import com.imatia.qsallcomponents.api.services.IEmployeeService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeRestControllerTest {

    @InjectMocks
    EmployeeRestController employeeRestController;

    @Mock
    IEmployeeService employeeService;

    @Nested
    class GetService{

        @Test
        void when_getService_expected_IEmployeeService(){
            IEmployeeService service = employeeRestController.getService();
            assertEquals(employeeService,service);
        }
    }



}