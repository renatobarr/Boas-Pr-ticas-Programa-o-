package br.com.alura.teste.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import br.com.alura.service.AbrigoService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpResponse;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AbrigoServiceTest {
    
    @Mock
    private ClientHttpConfiguration client;
    
    @Mock
    private HttpResponse<String> response;
    
    private AbrigoService abrigoService;
    private final Gson gson = new Gson(); 
    private final Abrigo abrigo = new Abrigo("Teste", "61981880392", "abrigo_alura@gmail.com");

    @BeforeEach
    public void setup() {
        abrigoService = new AbrigoService(client);
        abrigo.setId(0L);
    }

    @Test
    public void deveVerificarSeDispararRequisicaoGetSeraChamado() throws Exception {
   
        String jsonResponse = gson.toJson(List.of(abrigo));
        when(response.body()).thenReturn(jsonResponse);
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);

     
        List<Abrigo> abrigos = abrigoService.listarAbrigo();

    
        assertNotNull(abrigos, "A lista de abrigos não pode ser nula.");
        assertEquals(1, abrigos.size(), "Deveria haver exatamente um abrigo na lista.");
        assertEquals("Teste", abrigos.get(0).getNome(), "O nome do abrigo não corresponde.");
        
   
        verify(client, times(1)).dispararRequisicaoGet(anyString());
    }
}
