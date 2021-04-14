package ws.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ontimize.jee.server.rest.ORestController;

import api.core.constants.entities.Test;
import api.core.services.IAdministrationService;

@RestController
@RequestMapping("/administration")
public class AdministrationRestController extends ORestController<IAdministrationService> {

	private static final String WHO = "who";
	private static final String UNKNOWN = "unknown";
	private static final String PERMISSIONS = "/permissions";
	private static final String TEST = "/test";

	@Autowired
	private IAdministrationService iAdministrationService;

	@Override
	public IAdministrationService getService() {
		return this.iAdministrationService;
	}
	
	@RequestMapping(value = AdministrationRestController.TEST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Test> test(@RequestParam(value = AdministrationRestController.WHO, required = false, defaultValue = AdministrationRestController.UNKNOWN) String who) {

		return ResponseEntity.ok(this.iAdministrationService.test(who));
	}

	@RequestMapping(value = AdministrationRestController.PERMISSIONS, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> permissions() {
		return ResponseEntity.ok(this.iAdministrationService.getServerPermissionsSql());
	}
}