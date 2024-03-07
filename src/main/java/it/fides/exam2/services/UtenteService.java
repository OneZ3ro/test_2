package it.fides.exam2.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fides.exam2.exceptions.NotFoundException;
import it.fides.exam2.models.dto.entities.UtenteDto;
import it.fides.exam2.models.entities.LibroEntity;
import it.fides.exam2.models.entities.UtenteEntity;
import it.fides.exam2.models.enums.Ruoli;
import it.fides.exam2.repositories.UtenteRepository;

@Service
public class UtenteService {
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired LibroService libroService;
	
	public UtenteEntity getUtenteById(Long id) {
		return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));	
	}
	
	public List<UtenteEntity> getUtenti() {
		return utenteRepository.findAll()
;	}
	
	public UtenteEntity getUtenteByEmail(String email) {
		return utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
	}
	
	public List<UtenteEntity> primoEsercizio() {
		List<UtenteEntity> result = new ArrayList<>();
		
		List<UtenteEntity> app = utenteRepository.getUtentiOrderByAge();
		result.add(app.get(0));
		result.add(app.get(1));
		
		boolean check = false;
		
		for(UtenteEntity utente : result) {
			if(!utente.getNome().startsWith("A") && !utente.getNome().startsWith("E") && !utente.getNome().startsWith("I") && !utente.getNome().startsWith("O") && !utente.getNome().startsWith("U")) {
				check = true;
			}
		}
		
		if(check) {
			for(UtenteEntity utente : result) {
				utente.setGiovani(true);
			}
		}
		return result;
	}
	
	public void esempioVoid(int primo, int secondo) {
		if(primo > secondo) {
			System.out.println(primo+secondo);
		} else if(secondo > primo) {
			System.out.println(secondo-primo);
		} else {
			System.out.println(primo*secondo);
		}
	}
	
	/*public UtenteEntity secondoEsercizio() {
		
		List<UtenteEntity> allUtenti = this.getUtenti();
		Map<Long, Integer> appMap = new TreeMap<>();
		UtenteEntity result;
		int app = 0;
		Long idUtenteFinal = 0L;
		for(int i = 0; i < allUtenti.size(); i++) {
			appMap.put(allUtenti.get(i).getIdUtente(), allUtenti.get(i).getLibriAquistati().size());
			int queryCount = utenteRepository.queryForCountSecondExercise(allUtenti.get(i).getIdUtente());
			if (queryCount != 0) {
				if(queryCount > app) {
					app = queryCount;
					idUtenteFinal = allUtenti.get(i).getIdUtente();
				}
			}
		}
		result = this.getUtenteById(idUtenteFinal);
		return result;
		if(app <= 10) {
			return result;
		} 
		else {
			int count = 0;
			for(int i = 0; i < result.getLibriAquistati().size(); i++) {
				if(result.getLibriAquistati().get(i).getPrezzo() > 50) {
					count++;
					
				}
			}
		}
		
	}*/
	
	public UtenteEntity updateUtenteByEmail(UtenteDto body, String email) {
		UtenteEntity result = this.getUtenteByEmail(email);
    	
    	if (result != null) {
    		List<LibroEntity> libri = new ArrayList<>();
    		
    		if(body.getNome() != null) {
    			result.setNome(body.getNome());
    		}
    		
    		if(body.getCognome() != null) {
    			result.setCognome(body.getCognome());
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
	
	public UtenteEntity secondoEsercizio() {
		UtenteEntity utenteFinal = this.getUtenteById(utenteRepository.seleziona1UntentiConPiuLibri());
		List<LibroEntity> libriApp = new ArrayList<>();
		List<Long> listaIdLibri = utenteRepository.selezionaLibriDa1Utente(utenteRepository.seleziona1UntentiConPiuLibri());
		for(int i = 0; i < listaIdLibri.size(); i++) {
			LibroEntity libro = libroService.getLibroById(listaIdLibri.get(i));
			if(libro.getPrezzo() >= 50) {
				libriApp.add(libro);
			}
		}
		
		if(libriApp.size() >= 3) {
			List<Long> listaIdUtenti = utenteRepository.seleziona3UntentiConMenoLibri();
			for(int i = 0; i < listaIdUtenti.size(); i++) {
				List<LibroEntity> app = new ArrayList<>();
				app.add(libriApp.get(i));
				UtenteEntity utente = this.getUtenteById(listaIdUtenti.get(i));
				utente.setLibriAquistati(libriApp);
				utenteRepository.save(utente);
				System.out.println("Modificato l'utente con id: " + utente.getIdUtente());
			}
		}
		return utenteFinal;
	}
}
