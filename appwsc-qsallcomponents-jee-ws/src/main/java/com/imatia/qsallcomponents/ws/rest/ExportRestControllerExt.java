package com.imatia.qsallcomponents.ws.rest;

import com.ontimize.jee.webclient.export.base.ExportRestController;
import com.ontimize.jee.webclient.export.base.ExportService;
import com.ontimize.jee.webclient.export.exception.ExportException;

public class ExportRestControllerExt extends ExportRestController {

    @Override
    protected ExportService configureExportService(String fileExtension) throws ExportException {
        if("csv".equals(fileExtension)){
            return loadServiceBean("CsvExportService");
        }
       return super.configureExportService(fileExtension);
    }
}
