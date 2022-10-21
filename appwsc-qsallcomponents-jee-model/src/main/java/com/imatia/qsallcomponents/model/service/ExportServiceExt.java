package com.imatia.qsallcomponents.model.service;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import com.ontimize.jee.webclient.export.base.BaseExportService;
import com.ontimize.jee.webclient.export.base.ExportQueryParameters;
import com.ontimize.jee.webclient.export.exception.ExportException;
import com.ontimize.jee.webclient.export.providers.ExportDataProvider;
import org.springframework.stereotype.Service;

import com.imatia.qsallcomponents.api.services.IExportServiceExt;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.webclient.export.base.ExcelExportService;

public class ExportServiceExt extends BaseExportService implements IExportServiceExt {

	@Override
	public File csvExport(EntityResult data, List<Object> columns) throws Exception {
		StringBuilder body = new StringBuilder();

		for (int j = 0; j < data.calculateRecordNumber(); j++) {
			Map record = data.getRecordValues(j);
			StringBuilder sbRow = new StringBuilder();
			for (int i = 0; i < columns.size(); i++) {
				Object sText = record.get(columns.get(i));
				if (sText != null) {
					sbRow.append(sText);
					sbRow.append(";");
				}
			}
			sbRow.append("\n");
			body.append(sbRow.toString());
		}

		String csvStringValue = body.toString();

		File csvFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".csv");
		FileWriter fw = new FileWriter(csvFile);
		fw.write(csvStringValue, 0, csvStringValue.length());
		fw.flush();
		fw.close();

		return csvFile;
	}

	@Override
	public File generateFile(ExportQueryParameters exportParam) throws ExportException {
		try {
			ExportDataProvider<EntityResult> dataProvider = getDataProvider();
			dataProvider.doQuery();
			EntityResult data = dataProvider.getData();
			List<Object> columns = exportParam.getQueryParam().getColumns();
			return csvExport(data,columns);
		} catch (Exception e) {
			throw new ExportException("Impossible to export CSV file",e);
		}
	}
}
