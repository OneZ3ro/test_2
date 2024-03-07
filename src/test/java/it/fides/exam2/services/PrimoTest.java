package it.fides.exam2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.fides.exam2.models.dto.entities.UtenteDto;
import it.fides.exam2.models.entities.UtenteEntity;
import it.fides.exam2.repositories.UtenteRepository;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PrimoTest {
	@Mock
    private UtenteRepository utenteRepositoryMock; // Simuliamo il comportamento di UtenteRepository

    @InjectMocks
    private UtenteService utenteService; // L'istanza di UtenteService da testare, con UtenteRepository simulato
    
	@Test
	public void testFindAll() {
		// Simuliamo il comportamento di UtenteRepository.findAll()
        List<UtenteEntity> utentiSimulati = new ArrayList<>();
        utentiSimulati.add(new UtenteEntity("Alice"));
        utentiSimulati.add(new UtenteEntity("Bob"));
        when(utenteRepositoryMock.findAll()).thenReturn(utentiSimulati);

        // Eseguiamo il metodo da testare
        List<UtenteEntity> utenti = utenteService.getUtenti();

        // Verifichiamo se il metodo ha restituito i risultati attesi
        assertEquals(2, utenti.size()); // Assumiamo che ci siano due utenti simulati
        assertEquals("Alice", utenti.get(0).getNome());
        assertEquals("Bob", utenti.get(1).getNome());

        // Verifichiamo che il metodo findAll di utenteRepositoryMock sia stato chiamato
        verify(utenteRepositoryMock).findAll();
	}
	
	@Test
	public void testFindByEmail() {
		UtenteEntity utente = new UtenteEntity();
		utente.setEmail("2288mail@gmail.com");
		utente.setNome("Gianfrancarlo");
		when(utenteRepositoryMock.findByEmail("2288mail@gmail.com")).thenReturn(Optional.of(utente));
		
		//UtenteEntity utenteFound = utenteService.getUtenteByEmail("2288mail@gmail.com");
		
		assertEquals("Gianfrancarlo", utenteService.getUtenteByEmail("2288mail@gmail.com").getNome());
	}
	
	@Test
	public void testUpdateUtente() {
		UtenteEntity utente = new UtenteEntity();
		utente.setEmail("2288mail@gmail.com");
		utente.setNome("Gianfrancarlo");
		utente.setCognome("Pipino");
		
		UtenteDto utenteDto = new UtenteDto();
		utenteDto.setNome("Ginopasticcio");
		utenteDto.setCognome("Domodossola");
		
		UtenteEntity utenteResult = new UtenteEntity();
		utenteResult.setEmail("2288mail@gmail.com");
		utenteResult.setNome("Ginopasticcio");
		utenteResult.setCognome("Domodossola");
		
		when(utenteRepositoryMock.findByEmail("2288mail@gmail.com")).thenReturn(Optional.of(utente));
		when(utenteService.updateUtenteByEmail(utenteDto, "2288mail@gmail.com")).thenReturn(utenteResult);
		
		assertEquals("Domodossola", utenteService.updateUtenteByEmail(utenteDto, "2288mail@gmail.com").getCognome());
	}
	
	@Test
    public void testEsempioVoid() {
        // Cattura l'output della console
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Esegui il metodo esempioVoid()
        utenteService.esempioVoid(2,3);

        // Verifica se l'output è quello che ci si aspetta
        assertEquals("1", outContent.toString().trim()); // Modifica "1" con l'output corretto
        
        // Ripristina lo stream di output standard
        System.setOut(System.out);
        
        ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent1));
        utenteService.esempioVoid(3, 2);
        assertEquals("5", outContent1.toString().trim());
        System.setOut(System.out);
        
        ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent2));
        utenteService.esempioVoid(3, 3);
        assertEquals("9", outContent2.toString().trim());
        System.setOut(System.out);

        
        // Ripristina lo stream di output standard
        System.setOut(System.out);
    }
	
}
