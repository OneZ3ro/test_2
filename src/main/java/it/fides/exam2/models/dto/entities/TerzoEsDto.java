package it.fides.exam2.models.dto.entities;

import java.sql.Date;
import java.time.LocalDate;

public class TerzoEsDto {
	private Long idLibro;
	private String titolo;
	private double prezzo;
	private Long copieVendute;
	private boolean bestSeller;
	private Date dataPubblicazione;
	private Long idAutore;
	private String nomeAutore;
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
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	public Long getIdAutore() {
		return idAutore;
	}
	public void setIdAutore(Long idAutore) {
		this.idAutore = idAutore;
	}
	public String getNomeAutore() {
		return nomeAutore;
	}
	public void setNomeAutore(String nomeAutore) {
		this.nomeAutore = nomeAutore;
	}
	
	
	
}
