package ws.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontimize.jee.webclient.export.OExportRestController;

import api.core.services.ICustomerService;
import api.core.services.IExportServiceExt;

@RestController
@RequestMapping("/customers")
public class CustomerRestController extends OExportRestController<ICustomerService, IExportServiceExt> {

	@Autowired
	private ICustomerService customerService;

	@Override
	public ICustomerService getService() {
		return this.customerService;
	}

}
