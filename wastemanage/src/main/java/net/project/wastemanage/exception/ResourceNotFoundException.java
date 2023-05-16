package net.project.wastemanage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) 
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldname;
	private Object fieldvalue;
	public ResourceNotFoundException(String resourceName, String fieldname, Object fieldvalue) {
		super(String.format("%s not found with %s : '%s'", resourceName, fieldname, fieldvalue));
		this.resourceName = resourceName;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	
	
	public String getFieldname() {
		return fieldname;
	}
	
	public Object getFieldvalue() {
		return fieldvalue;
	}
	
}
