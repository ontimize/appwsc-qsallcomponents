package ws.core.rest;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontimize.dto.EntityResult;
import com.ontimize.dto.EntityResultMapImpl;
import com.ontimize.jee.server.rest.ORestController;

import api.core.services.IBranchService;

@RestController
@RequestMapping("/branches")
public class BranchRestController extends ORestController<IBranchService> {

	@Autowired
	private IBranchService branchService;

	@Override
	public IBranchService getService() {
		return this.branchService;
	}

	@PostMapping(value = "plan")
	public ResponseEntity<EntityResult> handleFileUpload(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files, @RequestParam(name = "data", required = false) String data)
			throws JsonParseException, JsonMappingException, IOException {
		EntityResult result = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.NODATA_RESULT);

		HashMap<String, Object> keyValues = new HashMap<>();
		if (data != null) {
			keyValues = new ObjectMapper().readValue(data, HashMap.class);
		}

		if (files.length > 0) {
			for (MultipartFile file : files) {
				if (file.isEmpty()) {
					continue;
				}

				try {
					HashMap<String, Object> av = new HashMap<>();
					av.put("BRANCHPLAN", file.getBytes());
					av.put("BRANCH_PLAN", StringUtils.join(names, ", "));
					result = this.branchService.branchUpdate(av, keyValues);

					// // Create temporary file to store uploaded file
					// File tempFile = File.createTempFile(file.getOriginalFilename(), "");
					//
					// FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
					//
					// // Prepare response
					// Hashtable<String, Object> value = new Hashtable<>();
					// value.put("remoteFilePath", tempFile.getAbsolutePath());
					// result.addRecord(value);
				} catch (IOException e) {
					e.printStackTrace();
					result.setCode(EntityResult.OPERATION_WRONG);
					result.setMessage(e.getMessage());
					return new ResponseEntity<EntityResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			return new ResponseEntity<EntityResult>(result, HttpStatus.OK);
		} else {
			result.setCode(EntityResult.OPERATION_WRONG);
			result.setMessage("ERROR_NO_FILES_INSERTED");
			return new ResponseEntity<EntityResult>(result, HttpStatus.OK);
		}
	}

}
