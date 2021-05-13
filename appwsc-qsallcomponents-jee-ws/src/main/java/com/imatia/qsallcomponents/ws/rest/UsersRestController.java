package com.imatia.qsallcomponents.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imatia.qsallcomponents.api.services.IUserAndRoleService;
import com.imatia.qsallcomponents.openapi.api.IUsersApi;
import com.ontimize.jee.server.rest.ORestController;

@RestController
@RequestMapping("/users")
public class UsersRestController extends ORestController<IUserAndRoleService> implements IUsersApi {

	@Autowired
	private IUserAndRoleService iUserAndRoleService;

	@Override
	public IUserAndRoleService getService() {

		return this.iUserAndRoleService;
	}

	@Override
	public ResponseEntity<Void> login() {
		return ResponseEntity.ok().build();
	}
}
