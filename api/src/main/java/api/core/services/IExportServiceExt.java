package api.core.services;

import java.io.File;
import java.util.List;

import com.ontimize.dto.EntityResult;
import com.ontimize.jee.webclient.export.IExportService;

public interface IExportServiceExt extends IExportService {

	public File csvExport(EntityResult data, List<String> columns) throws Exception;

}
