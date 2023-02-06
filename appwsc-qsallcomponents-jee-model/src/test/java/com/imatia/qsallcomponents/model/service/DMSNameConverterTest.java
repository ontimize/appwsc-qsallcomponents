package com.imatia.qsallcomponents.model.service;

import com.ontimize.jee.common.naming.DMSNaming;
import com.ontimize.jee.server.dms.model.OFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DMSNameConverterTest {

    @InjectMocks
    DMSNameConverter dmsNameConverter;


    @Test
    void when_getFileIdColumn_expected_object(){
        Object object = DMSNaming.DOCUMENT_FILE_ID_DMS_DOCUMENT_FILE;

        Object fileIdColumn = dmsNameConverter.getFileIdColumn();

        assertEquals(object,fileIdColumn);

    }

    @Test
    void when_getFileNameColumn_expected_object(){
        Object object = DMSNaming.DOCUMENT_FILE_NAME;

        Object fileIdColumn = dmsNameConverter.getFileNameColumn();

        assertEquals(object,fileIdColumn);

    }

    @Test
    void when_getFileSizeColumn_expected_object(){
        Object object = DMSNaming.DOCUMENT_FILE_VERSION_FILE_SIZE;

        Object fileIdColumn = dmsNameConverter.getFileSizeColumn();

        assertEquals(object,fileIdColumn);

    }

    @Test
    void when_getCategoryIdColumn_expected_object(){
        Object object = DMSNaming.CATEGORY_ID_CATEGORY;

        Object fileIdColumn = dmsNameConverter.getCategoryIdColumn();

        assertEquals(object,fileIdColumn);

    }

    @Test
    void when_getCategoryNameColumn_expected_object(){
        Object object = DMSNaming.CATEGORY_CATEGORY_NAME;

        Object fileIdColumn = dmsNameConverter.getCategoryNameColumn();

        assertEquals(object,fileIdColumn);

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

        OFile oFile = dmsNameConverter.createOFile(params);

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

        List<?> fileColumns = dmsNameConverter.getFileColumns(columns);

        assertEquals(columns, fileColumns);

    }


    @Test
    void when_getCategoryColumns_receive_columns_expected_list() {
        List<String> columns = new ArrayList<>();
        columns.add(DMSNaming.CATEGORY_ID_CATEGORY);
        columns.add(DMSNaming.CATEGORY_CATEGORY_NAME);
        columns.add(DMSNaming.CATEGORY_ID_CATEGORY_PARENT);

        List<?> fileColumns = dmsNameConverter.getCategoryColumns(columns);

        assertEquals(columns, fileColumns);

    }


}