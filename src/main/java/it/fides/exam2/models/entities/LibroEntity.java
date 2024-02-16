package it.fides.exam2.models.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
@JsonIgnoreProperties({"utenti"})
public class LibroEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_libro")
	private Long idLibro;

	private String titolo;

	private double prezzo;

	@Column(name = "copie_vendute")
	private Long copieVendute;

	@Column(name = "best_seller")
	private boolean bestSeller;

	@Column(name = "data_pubblicazione")
	private LocalDate dataPubblicazione;

	private String autore;
	/*@ManyToOne
	@JoinColumn(name = "id_autore")
	private AutoreEntity autore;*/

	@ManyToMany
	@JoinTable(name = "utenti_libri", joinColumns = @JoinColumn(name = "id_libro"), inverseJoinColumns = @JoinColumn(name = "id_utente"))
	private List<UtenteEntity> utenti;

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public Long getCopieVendute() {
		return copieVendute;
	}

	public void setCopieVendute(Long copieVendute) {
		this.copieVendute = copieVendute;
	}

	public boolean isBestSeller() {
		return bestSeller;
	}

	public void setBestSeller(boolean bestSeller) {
		this.bestSeller = bestSeller;
	}

	public LocalDate getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(LocalDate dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	
	

	/*public AutoreEntity getAutore() {
		return autore;
	}

	public void setAutore(AutoreEntity autore) {
		this.autore = autore;
	}*/

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public List<UtenteEntity> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<UtenteEntity> utenti) {
		this.utenti = utenti;
	}

	@Override
	public String toString() {
		return "LibroEntity [idLibro=" + idLibro + ", titolo=" + titolo + ", prezzo=" + prezzo + ", copieVendute="
				+ copieVendute + ", bestSeller=" + bestSeller + ", dataPubblicazione=" + dataPubblicazione + ", autore="
				+ autore + ", utenti=" + utenti + "]";
	}

	
	
}
