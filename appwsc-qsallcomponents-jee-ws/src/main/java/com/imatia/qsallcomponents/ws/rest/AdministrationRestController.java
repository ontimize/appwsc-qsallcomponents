package com.imatia.qsallcomponents.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imatia.qsallcomponents.api.constants.entities.Test;
import com.imatia.qsallcomponents.api.services.IAdministrationService;
import com.imatia.qsallcomponents.openapi.IAdministrationApi;
import com.ontimize.jee.server.rest.ORestController;

@RestController
@RequestMapping("/administration")
public class AdministrationRestController extends ORestController<IAdministrationService> implements IAdministrationApi {

	@Autowired
	private IAdministrationService iAdministrationService;

	@Override
	public IAdministrationService getService() {
		return this.iAdministrationService;
	}
	
	@Override
	public ResponseEntity<Test> test(String who) {
		return ResponseEntity.ok(this.iAdministrationService.test(who));
	}

	@Override
	public ResponseEntity<String> permissions() {
		return ResponseEntity.ok(this.iAdministrationService.getServerPermissionsSql());
	}
}
