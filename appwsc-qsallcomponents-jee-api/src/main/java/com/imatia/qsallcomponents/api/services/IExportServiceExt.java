package com.imatia.qsallcomponents.api.services;

import java.io.File;
import java.util.List;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.webclient.export.base.IExcelExportService;

public interface IExportServiceExt extends IExcelExportService {

	public File csvExport(EntityResult data, List<String> columns) throws Exception;

}
