package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.api.services.IEmployeeService;
import com.imatia.qsallcomponents.model.dao.EmployeeDao;
import com.imatia.qsallcomponents.model.dao.EmployeeTypeDao;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes={
        EmployeeService.class,
        EmployeeDao.class,
        EmployeeTypeDao.class
})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
public class EmployeeServiceIT {

    @Autowired
    IEmployeeService iEmployeeService;

    @Nested
    class EmployeeQuery{

        @Test
        void when_employeeQuery_receive(){
            //iEmployeeService.employeeQuery();
        }
    }


}
