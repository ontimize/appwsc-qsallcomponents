package com.imatia.qsallcomponents.model.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountDaoTest {


    @InjectMocks
    AccountDao accountDao;

    @Test
    void when_calculateCDID_receive_first_innerAccount_expect_int (){

        accountDao.calculateCDID(String.valueOf(1),2);

    }

    @Test
    void when_calculateDCUniqueDigit_receive_numberString_expect_int(){
        String numberString = "numberString";
       // accountDao.calculateDCUniqueDigit(numberString);
    }



}