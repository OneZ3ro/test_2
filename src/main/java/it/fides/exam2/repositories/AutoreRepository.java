package it.fides.exam2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.fides.exam2.models.entities.AutoreEntity;
import it.fides.exam2.models.entities.LibroEntity;

@Repository
public interface AutoreRepository extends JpaRepository<AutoreEntity, Long>{
	@Query(value = "SELECT l.*, a.nome FROM libro l join autore a on l.id_autore = a.id_autore order by a.nome, l.prezzo Desc;", nativeQuery = true)
	public List<Object> queryForThirdExercise();
	
}
