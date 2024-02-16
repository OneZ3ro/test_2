package it.fides.exam2.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.fides.exam2.exceptions.BadRequestException;
import it.fides.exam2.exceptions.NotFoundException;
import it.fides.exam2.exceptions.UnauthorizedException;
import it.fides.exam2.models.dto.entities.UtenteDto;
import it.fides.exam2.models.entities.LibroEntity;
import it.fides.exam2.models.entities.UtenteEntity;
import it.fides.exam2.models.enums.Ruoli;
import it.fides.exam2.repositories.UtenteRepository;
import it.fides.exam2.security.JWTTools;

@Service
public class AuthService {
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
    private UtenteRepository utenteRepository;

	@Autowired
	private JWTTools jwtTools;

	@Autowired
	private PasswordEncoder bcrypt;
	
	@Autowired
	private LibroService libroService;
	

	public String authenticateUser(UtenteDto body) throws Exception {
		UtenteEntity user = utenteService.getUtenteByEmail(body.getEmail());
		if (bcrypt.matches(body.getPassword(), user.getPassword())) {
			return jwtTools.createToken(user);
		} else {
			throw new UnauthorizedException("Credenziali non valide!");
		}
	}
	
	public UtenteEntity registerUser(UtenteDto body) throws IOException, BadRequestException {
		utenteRepository.findByEmail(body.getEmail()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });
		UtenteEntity result = new UtenteEntity();
		List<LibroEntity> libri = new ArrayList<>();
		
		if(body.getNome() != null) {
			result.setNome(body.getNome());
		} else {
			throw new BadRequestException("Devi passare necessariamente il nome");
		}
		
		if(body.getCognome() != null) {
			result.setCognome(body.getCognome());
		} else {
			throw new BadRequestException("Devi passare necessariamente il cognome");
		}
		
		if(body.getEmail() != null) {
			result.setEmail(body.getEmail());
		} else {
			throw new BadRequestException("Devi passare necessariamente l'email");
		}
		
		if(body.getPassword() != null) {
			result.setPassword(bcrypt.encode(body.getPassword()));
		} else {
			throw new BadRequestException("Devi passare necessariamente la password");
		}
		
		result.setAge(body.getAge());
		result.setGiovani(false);
		result.setRuolo(Ruoli.CLIENTE);
		
		if(body.getLibriAcquistati() != null) {
			
			for(Object obj : body.getLibriAcquistati()) {
				LibroEntity lib = libroService.getLibroById((Long)obj);
				libri.add(lib);
			}
		}
		result.setLibriAquistati(libri);
		
		return utenteRepository.save(result);
    }
	
	
	public UtenteEntity updateUtenteById(UtenteDto body, Long id) {
		UtenteEntity result = utenteService.getUtenteById(id);
    	
    	if (result != null) {
    		List<LibroEntity> libri = new ArrayList<>();
    		
    		if(body.getNome() != null) {
    			result.setNome(body.getNome());
    		}
    		
    		if(body.getCognome() != null) {
    			result.setCognome(body.getCognome());
    		} 
    		
    		if(body.getEmail() != null) {
    			result.setEmail(body.getEmail());
    		} 
    		
    		if(body.getPassword() != null) {
    			result.setPassword(bcrypt.encode(body.getPassword()));
    		} 
    		
    		if(body.getAge() != null) {
    			result.setAge(body.getAge());
    		}
    		
    		result.setGiovani(false);
    		result.setRuolo(Ruoli.CLIENTE);
    		
    		if(body.getLibriAcquistati() != null) {
    			
    			for(int i = 0; i < body.getLibriAcquistati().size(); i++) {
    				LibroEntity lib = libroService.getLibroById(body.getLibriAcquistati().get(i));
    				libri.add(lib);
    			}
    		}
    		result.setLibriAquistati(libri);
    		
    		
    	}
    	
    	return utenteRepository.save(result);
    	
    }

}
