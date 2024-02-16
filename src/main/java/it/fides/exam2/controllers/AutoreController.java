package it.fides.exam2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.fides.exam2.models.dto.entities.AutoreDto;
import it.fides.exam2.models.dto.entities.TerzoEsDto;
import it.fides.exam2.models.entities.AutoreEntity;
import it.fides.exam2.models.entities.LibroEntity;
import it.fides.exam2.services.AutoreService;

@RestController
@RequestMapping("/autori")
public class AutoreController {
	@Autowired
	private AutoreService autoreService;
	
	@PostMapping
	public AutoreEntity insertAutore (@RequestBody AutoreDto body) {
		return autoreService.insertAutore(body);
	}
	
	/*@GetMapping("/terzoEsercizio")
	public List<TerzoEsDto> terzoEsercizio () {
		return autoreService.terzoEsercizio();
	}*/
}
