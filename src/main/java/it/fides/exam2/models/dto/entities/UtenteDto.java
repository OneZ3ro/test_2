package it.fides.exam2.models.dto.entities;

import java.util.List;

public class UtenteDto {
	private Long idUtente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private Integer age;
	private String ruolo;
	private List<Long> libriAcquistati;

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public List<Long> getLibriAcquistati() {
		return libriAcquistati;
	}

	public void setLibriAcquistati(List<Long> libriAcquistati) {
		this.libriAcquistati = libriAcquistati;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
