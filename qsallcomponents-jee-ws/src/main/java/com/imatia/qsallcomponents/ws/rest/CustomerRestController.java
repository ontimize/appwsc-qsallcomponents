package com.imatia.qsallcomponents.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imatia.qsallcomponents.api.services.ICustomerService;
import com.imatia.qsallcomponents.api.services.IExportServiceExt;
import com.ontimize.jee.webclient.export.OExportRestController;

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
