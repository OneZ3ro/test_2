package it.fides.exam2.models.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.fides.exam2.models.enums.Ruoli;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "utente")
public class UtenteEntity implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utente")
	private Long idUtente;
	
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private Integer age;
	private boolean giovani;
	@Enumerated(EnumType.STRING)
	private Ruoli ruolo;
	
	@ManyToMany
	@JoinTable(name = "utenti_libri", joinColumns = @JoinColumn(name = "id_utente"), inverseJoinColumns = @JoinColumn(name = "id_libro"))
	private List<LibroEntity> libriAquistati;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
        if (this.ruolo != null) {
            authorities.add(new SimpleGrantedAuthority(ruolo.name()));
        }
        return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

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

	public Ruoli getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruoli ruolo) {
		this.ruolo = ruolo;
	}

	public List<LibroEntity> getLibriAquistati() {
		return libriAquistati;
	}

	public void setLibriAquistati(List<LibroEntity> libriAquistati) {
		this.libriAquistati = libriAquistati;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isGiovani() {
		return giovani;
	}

	public void setGiovani(boolean giovani) {
		this.giovani = giovani;
	}

	@Override
	public String toString() {
		return "UtenteEntity [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
				+ ", password=" + password + ", age=" + age + ", giovani=" + giovani + ", ruolo=" + ruolo
				+ ", libriAquistati=" + libriAquistati + "]";
	}

	
	
	
}
