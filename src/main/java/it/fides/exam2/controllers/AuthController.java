package it.fides.exam2.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import it.fides.exam2.exceptions.BadRequestException;
import it.fides.exam2.models.dto.entities.UtenteDto;
import it.fides.exam2.models.dto.entities.UtenteLoginSuccessDto;
import it.fides.exam2.models.entities.UtenteEntity;
import it.fides.exam2.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UtenteLoginSuccessDto login(@RequestBody UtenteDto body, HttpServletResponse response) throws Exception {
    	//imposto cookie
        Cookie cookie = new Cookie("jwt", authService.authenticateUser(body));
        cookie.setPath("/"); // Set the cookie's path
        cookie.setHttpOnly(true); // Make sure the cookie is accessible only through HTTP (not JavaScript)
        cookie.setMaxAge(3600); // Set the expiration time of the cookie (in seconds)
        response.addCookie(cookie);

        return new UtenteLoginSuccessDto(authService.authenticateUser(body));
    }
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public UtenteEntity saveUser(@RequestBody @Validated UtenteDto body, BindingResult validation) throws BadRequestException, IOException{
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerUser(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
