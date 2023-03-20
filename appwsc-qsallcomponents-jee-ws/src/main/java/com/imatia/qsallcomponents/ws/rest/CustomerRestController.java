package com.imatia.qsallcomponents.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imatia.qsallcomponents.api.services.ICustomerService;
import com.imatia.qsallcomponents.openapi.ICustomersApi;
import com.ontimize.jee.server.rest.ORestController;

@RestController
@RequestMapping("/customers")
public class CustomerRestController extends ORestController<ICustomerService> implements ICustomersApi {

	@Autowired
	private ICustomerService customerService;

	@Override
	public ICustomerService getService() {

		return this.customerService;
	}
}
