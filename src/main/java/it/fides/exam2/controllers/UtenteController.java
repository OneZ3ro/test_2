package it.fides.exam2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.fides.exam2.models.dto.entities.UtenteDto;
import it.fides.exam2.models.entities.UtenteEntity;
import it.fides.exam2.services.AuthService;
import it.fides.exam2.services.UtenteService;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private AuthService authService;
	
	@GetMapping
	public List<UtenteEntity> getAllUtenti() {
		return utenteService.getUtenti()
;	}
	
	@GetMapping("/me")
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentUser){
        return currentUser;
    };
    
    @PutMapping("/{id}")
    public UtenteEntity updateProfileUser(@RequestBody UtenteDto body, @PathVariable Long id) {
        return authService.updateUtenteById(body, id);
    }
    
    @GetMapping("/{id}")
    public UtenteEntity getUtente(@PathVariable Long id) {
        return utenteService.getUtenteById(id);
    }
    
    @GetMapping("/primoEsercizio")
    public List<UtenteEntity> primoEsercizio() {
    	return utenteService.primoEsercizio();
    }
    
}
