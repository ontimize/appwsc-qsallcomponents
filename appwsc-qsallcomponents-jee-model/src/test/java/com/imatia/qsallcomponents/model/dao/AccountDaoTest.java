package com.imatia.qsallcomponents.model.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AccountDaoTest {


    @InjectMocks
    AccountDao accountDao;

    @Test
    void when_calculateCDID_receive_first_innerAccount_expect_int() {

        int actual = accountDao.calculateCDID(String.valueOf(1), 2);
        assertEquals(19, actual);

    }

    @Test
    void when_calculateDCUniqueDigit_receive_numberString_expect_int() {
        String numberString = "123456789";
        int actual = accountDao.calculateDCUniqueDigit(numberString);
        int expected = 6;

        assertEquals(expected, actual);
    }


}