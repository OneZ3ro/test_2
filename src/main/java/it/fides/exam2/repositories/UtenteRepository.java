package it.fides.exam2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.fides.exam2.models.entities.UtenteEntity;

@Repository
public interface UtenteRepository extends JpaRepository<UtenteEntity, Long>{
	Optional<UtenteEntity> findByEmail(String email);
	
	@Query(value = "SELECT * FROM utente ORDER BY age", nativeQuery = true)
	public List<UtenteEntity> getUtentiOrderByAge();
	
	@Query(value = "Select id_utente from utenti_libri group by id_utente order by count(id_utente) desc limit 1;", nativeQuery = true)
	public Long seleziona1UntentiConPiuLibri();
	
	@Query(value = "Select id_libro from utenti_libri where id_utente = ?;", nativeQuery = true)
	public List<Long> selezionaLibriDa1Utente(Long id);
	
	@Query(value = "SELECT id_utente\r\n"
			+ "from (Select u.id_utente, ul.id_libro from utente u left join utenti_libri ul on u.id_utente = ul.id_utente) ul \r\n"
			+ "group by id_utente order by count(ul.id_utente) Asc limit 3", nativeQuery = true)
	public List<Long> seleziona3UntentiConMenoLibri();
	
}
