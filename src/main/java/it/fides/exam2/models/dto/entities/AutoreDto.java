package it.fides.exam2.models.dto.entities;

import java.time.LocalDate;
import java.util.Set;

public class AutoreDto {
	private Long idAutore;
	private String nome;
	private String cognome;
	private Integer age;
	private LocalDate dataDiNascita;
	private Set<Object> libri;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public Set<Object> getLibri() {
		return libri;
	}

	public void setLibri(Set<Object> libri) {
		this.libri = libri;
	}

}
