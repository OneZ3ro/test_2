package it.fides.exam2.models.dto.entities;

import java.time.LocalDate;
import java.util.List;

public class LibroDto {
	private Long idLibro;
	private String titolo;
	private double prezzo;
	private double prezzoScontato;
	private Long copieVendute;
	private boolean bestSeller;
	private LocalDate dataPubblicazione;
	private String autore;
	private List<Long> idUtenti;
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
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public List<Long> getIdUtenti() {
		return idUtenti;
	}
	public void setIdUtenti(List<Long> idUtenti) {
		this.idUtenti = idUtenti;
	}
	public double getPrezzoScontato() {
		return prezzoScontato;
	}
	public void setPrezzoScontato(double prezzoScontato) {
		this.prezzoScontato = prezzoScontato;
	}
	
}
