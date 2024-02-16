package it.fides.exam2.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ObjectError> errorList;
    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException(List<ObjectError> errorList) {
        this.errorList = errorList;
    }
	public List<ObjectError> getErrorList() {
		return errorList;
	}
    
}
