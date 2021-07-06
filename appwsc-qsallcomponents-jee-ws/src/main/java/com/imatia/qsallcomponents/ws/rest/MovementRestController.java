package com.imatia.qsallcomponents.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imatia.qsallcomponents.api.services.IMovementService;
import com.imatia.qsallcomponents.openapi.IMovementsApi;
import com.ontimize.jee.server.rest.ORestController;

@RestController
@RequestMapping("/movements")
public class MovementRestController extends ORestController<IMovementService> implements IMovementsApi {

	@Autowired private IMovementService movementService;

	@Override
	public IMovementService getService() {
		return this.movementService;
	}
}
