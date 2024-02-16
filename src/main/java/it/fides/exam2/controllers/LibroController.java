package it.fides.exam2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.fides.exam2.models.dto.entities.LibroDto;
import it.fides.exam2.models.entities.LibroEntity;
import it.fides.exam2.services.LibroService;

@RestController
@RequestMapping("/libri")
public class LibroController {

	@Autowired
	private LibroService libroService;
	
	@GetMapping("/{id}")
	public LibroEntity getLibro (@PathVariable Long id) {
		return libroService.getLibroById(id);
	}
	
	@PostMapping
	public LibroEntity inserLibro(@RequestBody LibroDto body) {
		return libroService.insertLibro(body);
	}
	
	@GetMapping("/terzoEsercizio")
	public List<LibroEntity> terzoEsercizio(){
		return libroService.terzoEsercizio();
	}
	
	@GetMapping("/quartoEsercizio")
	public List<LibroEntity> quartoEsercizio(@RequestParam("nomeAutore") String autore) {
		return libroService.quartoEsercizio(autore);
	}
}
