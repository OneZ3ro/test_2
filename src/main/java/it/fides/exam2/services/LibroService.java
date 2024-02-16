package it.fides.exam2.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fides.exam2.exceptions.NotFoundException;
import it.fides.exam2.models.dto.entities.LibroDto;
import it.fides.exam2.models.entities.LibroEntity;
import it.fides.exam2.models.entities.UtenteEntity;
import it.fides.exam2.repositories.LibroRepository;

@Service
public class LibroService {
	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutoreService autoreService;

	public LibroEntity getLibroById(Long id) {
		return libroRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public List<LibroEntity> getLibri() {
		return libroRepository.findAll();
	}

	public LibroEntity insertLibro(LibroDto body) {
		LibroEntity result = new LibroEntity();
		List<UtenteEntity> utenti = new ArrayList<>();
		result.setTitolo(body.getTitolo());
		result.setPrezzo(body.getPrezzo());
		result.setCopieVendute(body.getCopieVendute());
		if (body.getCopieVendute() > 100) {
			result.setBestSeller(true);
		} else {
			result.setBestSeller(false);
		}
		result.setDataPubblicazione(body.getDataPubblicazione());
		result.setAutore(body.getAutore());
		result.setUtenti(utenti);
		return libroRepository.save(result);
	}

	public List<LibroEntity> terzoEsercizio() {
		return libroRepository.queryForThirdExercise();
	}

	public List<LibroEntity> quartoEsercizio(String autore) {
		List<LibroEntity> result = libroRepository.queryForFourthExercise(autore);
		List<LibroEntity> app = new ArrayList<>();
		for(int i = 0; i < result.size();i++) {
			LibroEntity libro = result.get(i);
			if(result.get(i).isBestSeller()) {
				int annoCorrente = LocalDate.now().getYear();
				int annoPassato = result.get(i).getDataPubblicazione().getYear();
				int minus = annoCorrente - annoPassato;
				if (minus >= 10) {
					result.get(i).setPrezzo(result.get(i).getPrezzo()*0.8);
					libro.setPrezzo(result.get(i).getPrezzo()*0.8);
				}
			}
			app.add(libro);
		}
		return app;
	}
}
