package it.fides.exam2.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fides.exam2.exceptions.NotFoundException;
import it.fides.exam2.models.dto.entities.AutoreDto;
import it.fides.exam2.models.dto.entities.TerzoEsDto;
import it.fides.exam2.models.entities.AutoreEntity;
import it.fides.exam2.models.entities.LibroEntity;
import it.fides.exam2.repositories.AutoreRepository;
import it.fides.exam2.repositories.LibroRepository;

@Service
public class AutoreService {
	@Autowired
	private AutoreRepository autoreRepository;

	@Autowired
	private LibroRepository libroRepository;

	public AutoreEntity getAutoreById(Long id) {
		return autoreRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public List<AutoreEntity> getAutori() {
		return autoreRepository.findAll();
	}

	public AutoreEntity insertAutore(AutoreDto body) {
		AutoreEntity result = new AutoreEntity();
		Set<LibroEntity> app = new TreeSet<>();
		result.setNome(body.getNome());
		result.setCognome(body.getCognome());
		result.setAge(body.getAge());
		result.setDataDiNascita(body.getDataDiNascita());
		result.setLibri(app);
		return autoreRepository.save(result);
	}

	/*public List<TerzoEsDto> terzoEsercizio() {
		List<TerzoEsDto> result = new ArrayList<>();
		for (int i = 0; i < autoreRepository.queryForThirdExercise().size(); i++) {
			Object[] app = (Object[])autoreRepository.queryForThirdExercise().get(i);
				TerzoEsDto terzo = new TerzoEsDto();
				terzo.setIdLibro((Long)app[0]);
				if((Long)app[2] == 0) {
					terzo.setBestSeller(false);
				} else {
					terzo.setBestSeller(true);
				}
				terzo.setBestSeller((boolean)app[1]);
				terzo.setCopieVendute((Long)app[2]);
				terzo.setDataPubblicazione((Date)app[3]);
				terzo.setPrezzo((double)app[4]);
				terzo.setTitolo((String)app[5]);
				terzo.setIdAutore((Long)app[6]);
				terzo.setNomeAutore((String)app[7]);
				result.add(terzo);
			/*for(int k = 0; k < app.length; k++) {
				System.out.println("weeeeeeeeeeeeeeee   " + app[k]);
			}
		}
		return result;
	}*/

	/*
	 * public List<AutoreEntity> terzoEsercizio() { List<AutoreEntity> allAutori =
	 * this.getAutori(); for(int i = 0; i < allAutori.size(); i++) {
	 * 
	 * } }
	 */
}
