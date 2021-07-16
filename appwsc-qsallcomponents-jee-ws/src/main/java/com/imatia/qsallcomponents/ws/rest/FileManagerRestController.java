package com.imatia.qsallcomponents.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontimize.jee.common.services.dms.IDMSService;
import com.ontimize.jee.server.dms.rest.DMSRestController;
import com.ontimize.jee.server.dms.rest.IDMSNameConverter;

@RestController
@RequestMapping("/filemanager")
public class FileManagerRestController extends DMSRestController<IDMSService, IDMSNameConverter> {

	@Autowired
	private IDMSService dmsService;

	@Override
	public IDMSService getService() {
		return this.dmsService;
	}

}
