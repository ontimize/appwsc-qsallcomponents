package com.imatia.qsallcomponents.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imatia.qsallcomponents.api.services.IEmployeeService;
import com.imatia.qsallcomponents.openapi.api.IEmployeesApi;
import com.ontimize.jee.server.rest.ORestController;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController extends ORestController<IEmployeeService> implements IEmployeesApi {

	@Autowired private IEmployeeService employeeService;

	@Override
	public IEmployeeService getService() {
		return this.employeeService;
	}
}
