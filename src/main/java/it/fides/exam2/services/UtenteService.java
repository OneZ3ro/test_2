package it.fides.exam2.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fides.exam2.exceptions.NotFoundException;
import it.fides.exam2.models.entities.UtenteEntity;
import it.fides.exam2.repositories.UtenteRepository;

@Service
public class UtenteService {
	@Autowired
	private UtenteRepository utenteRepository;
	
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
			if(!utente.getNome().startsWith("a") && !utente.getNome().startsWith("e") && !utente.getNome().startsWith("i") && !utente.getNome().startsWith("o") && !utente.getNome().startsWith("u")) {
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
	
	public UtenteEntity secondoEsercizio() {
		List<UtenteEntity> allUtenti = this.getUtenti();
		//Map<Long, Integer> appMap = new TreeMap<>();
		UtenteEntity result;
		int app = 0;
		Long idUtenteFinal = 0L;
		for(int i = 0; i < allUtenti.size(); i++) {
			//appMap.put(allUtenti.get(i).getIdUtente(), allUtenti.get(i).getLibriAquistati().size());
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
		/*if(app <= 10) {
			return result;
		} 
		else {
			int count = 0;
			for(int i = 0; i < result.getLibriAquistati().size(); i++) {
				if(result.getLibriAquistati().get(i).getPrezzo() > 50) {
					count++;
					
				}
			}
		}*/
	}
}
