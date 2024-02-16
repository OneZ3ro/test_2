package it.fides.exam2.models.entities;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autore")
public class AutoreEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_autore")
	private Long idAutore;
	
	private String nome;
	
	private String cognome;
	
	private Integer age;
	
	@Column(name = "data_di_nascita")
	private LocalDate dataDiNascita;
	
	@OneToMany(mappedBy = "autore")
	private Set<LibroEntity> libri;

	public Long getIdAutore() {
		return idAutore;
	}

	public void setIdAutore(Long idAutore) {
		this.idAutore = idAutore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<LibroEntity> getLibri() {
		return libri;
	}

	public void setLibri(Set<LibroEntity> libri) {
		this.libri = libri;
	}

	@Override
	public String toString() {
		return "AutoreEntity [idAutore=" + idAutore + ", nome=" + nome + ", cognome=" + cognome + ", dataDiNascita="
				+ dataDiNascita + ", age=" + age + "]";
	}
	
	
}
