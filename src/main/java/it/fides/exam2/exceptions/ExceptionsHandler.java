package it.fides.exam2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import it.fides.exam2.models.dto.exceptions.ErrorsDto;
import it.fides.exam2.models.dto.exceptions.ErrorsWithListDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsWithListDto handleBadRequest(BadRequestException e) {
        if (e.getErrorList() != null) {
            List<String> errorsList = e.getErrorList().stream().map(objectError -> objectError.getDefaultMessage()).toList();
            return new ErrorsWithListDto(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsWithListDto(e.getMessage(), new Date(), new ArrayList<>());
        }

    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsDto handleUnauthorized(UnauthorizedException e) {
        return new ErrorsDto(e.getMessage(), new Date());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    public ErrorsDto handleAccessDenied(AccessDeniedException e) {
        return new ErrorsDto(e.getMessage(), new Date());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    public ErrorsDto handleNotFound(NotFoundException e) {
        return new ErrorsDto(e.getMessage(), new Date());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    public ErrorsDto handleGeneric(Exception e) {
        e.printStackTrace();
        return new ErrorsDto("Problema lato server (Internal Server Error)", new Date());
    }
}