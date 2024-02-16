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
	
	@Query(value = "Select count(id_utente) from utenti_libri where id_utente = ?;", nativeQuery = true)
	public int queryForCountSecondExercise(Long id);
}
