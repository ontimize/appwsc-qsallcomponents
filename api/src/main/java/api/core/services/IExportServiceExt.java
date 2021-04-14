package api.core.services;

import java.io.File;
import java.util.List;

import com.ontimize.db.EntityResult;
import com.ontimize.jee.webclient.export.IExportService;

public interface IExportServiceExt extends IExportService {

	public File csvExport(EntityResult data, List<String> columns) throws Exception;

}
