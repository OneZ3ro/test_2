package it.fides.exam2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.fides.exam2.models.entities.LibroEntity;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long>{
	@Query(value = "SELECT * FROM libro order by autore, prezzo Desc;", nativeQuery = true)
	public List<LibroEntity> queryForThirdExercise();
	
	@Query(value = "SELECT * FROM libro where autore = ? order by data_pubblicazione", nativeQuery = true)
	public List<LibroEntity> queryForFourthExercise(String autore);
}
