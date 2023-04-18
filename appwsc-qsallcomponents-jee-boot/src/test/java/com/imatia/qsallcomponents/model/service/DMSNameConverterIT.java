package com.imatia.qsallcomponents.model.service;


import com.imatia.qsallcomponents.model.dao.CustomerAccountDao;
import com.imatia.qsallcomponents.model.dao.CustomerDao;
import com.imatia.qsallcomponents.model.dao.CustomerTypeDao;
import com.ontimize.jee.common.naming.DMSNaming;
import com.ontimize.jee.common.services.dms.IDMSService;
import com.ontimize.jee.server.dms.model.OFile;
import com.ontimize.jee.server.dms.rest.IDMSNameConverter;
import com.ontimize.jee.server.services.dms.DMSCreationHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {CustomerService.class, CustomerDao.class,
        CustomerTypeDao.class,
        CustomerAccountDao.class,
        DMSCreationHelper.class,
        DMSNameConverter.class
})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DMSNameConverterIT {


    @MockBean
    IDMSService idmsService;

    @Autowired
    IDMSNameConverter idmsNameConverter;

    @Autowired
    DataSource dataSource;

    @Test
    void when_getFileIdColumn_expected_object() {
        Object object = DMSNaming.DOCUMENT_FILE_ID_DMS_DOCUMENT_FILE;

        Object fileIdColumn = idmsNameConverter.getFileIdColumn();

        assertEquals(object, fileIdColumn);
        assertNotNull(fileIdColumn);

    }

    @Test
    void when_getFileNameColumn_expected_object() {
        Object object = DMSNaming.DOCUMENT_FILE_NAME;

        Object fileIdColumn = idmsNameConverter.getFileNameColumn();

        assertEquals(object, fileIdColumn);
        assertNotNull(fileIdColumn);


    }

    @Test
    void when_getFileSizeColumn_expected_object() {
        Object object = DMSNaming.DOCUMENT_FILE_VERSION_FILE_SIZE;

        Object fileIdColumn = idmsNameConverter.getFileSizeColumn();

        assertEquals(object, fileIdColumn);
        assertNotNull(fileIdColumn);


    }

    @Test
    void when_getCategoryIdColumn_expected_object() {
        Object object = DMSNaming.CATEGORY_ID_CATEGORY;

        Object fileIdColumn = idmsNameConverter.getCategoryIdColumn();

        assertEquals(object, fileIdColumn);
        assertNotNull(fileIdColumn);


    }

    @Test
    void when_getCategoryNameColumn_expected_object() {
        Object object = DMSNaming.CATEGORY_CATEGORY_NAME;

        Object fileIdColumn = idmsNameConverter.getCategoryNameColumn();

        assertEquals(object, fileIdColumn);
        assertNotNull(fileIdColumn);


    }


    @Test
    void when_createOFile_receive_params_expected_OFile() {
        Map<Object, Object> params = new HashMap<>();
        params.put(DMSNaming.DOCUMENT_FILE_ID_DMS_DOCUMENT_FILE, 1);
        params.put(DMSNaming.DOCUMENT_FILE_NAME, "");
        params.put(DMSNaming.DOCUMENT_FILE_TYPE, "");
        params.put(DMSNaming.DOCUMENT_FILE_VERSION_FILE_SIZE, 2);
        params.put(DMSNaming.DOCUMENT_FILE_VERSION_FILE_ADDED_DATE, new Date());

        OFile file = new OFile();

        file.setId((Integer) params.get(DMSNaming.DOCUMENT_FILE_ID_DMS_DOCUMENT_FILE));
        file.setName((String) params.get(DMSNaming.DOCUMENT_FILE_NAME));
        file.setType((String) params.get(DMSNaming.DOCUMENT_FILE_TYPE));
        file.setSize((Integer) params.get(DMSNaming.DOCUMENT_FILE_VERSION_FILE_SIZE));
        file.setCreationDate(((Date) params.get(DMSNaming.DOCUMENT_FILE_VERSION_FILE_ADDED_DATE)).getTime());
        file.setDirectory(false);

        OFile oFile = idmsNameConverter.createOFile(params);
        assertNotNull(oFile);

        Assertions.assertAll(() -> {
                    assertEquals(file.getId(), oFile.getId());
                },
                () -> {
                    assertEquals(file.getName(), oFile.getName());
                },
                () -> {
                    assertEquals(file.getType(), oFile.getType());
                },
                () -> {
                    assertEquals(file.getSize(), oFile.getSize());
                },
                () -> {
                    assertEquals(file.getCreationDate(), oFile.getCreationDate());
                }
        );

    }


    @Test
    void when_getFileColumns_receive_columns_expected_list() {
        List<String> columns = new ArrayList<>();
        columns.add(DMSNaming.DOCUMENT_FILE_ID_DMS_DOCUMENT_FILE);
        columns.add(DMSNaming.DOCUMENT_FILE_NAME);
        columns.add(DMSNaming.DOCUMENT_FILE_TYPE);
        columns.add(DMSNaming.DOCUMENT_FILE_VERSION_FILE_SIZE);
        columns.add(DMSNaming.DOCUMENT_FILE_VERSION_FILE_ADDED_DATE);

        List<?> fileColumns = idmsNameConverter.getFileColumns(columns);

        assertEquals(columns, fileColumns);
        assertNotNull(fileColumns);


    }


    @Test
    void when_getCategoryColumns_receive_columns_expected_list() {
        List<String> columns = new ArrayList<>();
        columns.add(DMSNaming.CATEGORY_ID_CATEGORY);
        columns.add(DMSNaming.CATEGORY_CATEGORY_NAME);
        columns.add(DMSNaming.CATEGORY_ID_CATEGORY_PARENT);

        List<?> fileColumns = idmsNameConverter.getCategoryColumns(columns);

        assertEquals(columns, fileColumns);
        assertNotNull(fileColumns);


    }

}
