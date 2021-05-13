package com.casaSolaire.controllers;


import com.casaSolaire.dto.ClientDto;
import com.casaSolaire.dto.CommandeDto;
import com.casaSolaire.services.CommandeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommandeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CommandeService commandeService;

    @InjectMocks
    private CommandeController commandeController;

    private ClientDto clientDto;
    private CommandeDto commandeDto;
    private List<CommandeDto> commandeDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        clientDto = new ClientDto();
        clientDto.setReference("cl1");
        clientDto.setLastName("diallo");
        commandeDto = new CommandeDto();
        commandeDto.setId(1L);
        commandeDto.setClientDto(clientDto);

        mockMvc = MockMvcBuilders.standaloneSetup(commandeController).build();
    }

    @After
    public void tearDown() {
        commandeDto = null;
    }

    @Test
    public void PostMappingOfCommande() throws Exception {
        when(commandeService.save(any())).thenReturn(commandeDto);
        mockMvc.perform(post("/casa-solaire/v1/commandes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(commandeDto)))
                .andExpect(status().isOk());
        verify(commandeService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllCommandes() throws Exception {
        when(commandeService.findAll()).thenReturn(commandeDtoList);
        mockMvc.perform(get("/casa-solaire/v1/commandes/all").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(commandeDto))).
                andDo(MockMvcResultHandlers.print());
        verify(commandeService).findAll();
        verify(commandeService, times(1)).findAll();
    }

    @Test
    public void GetMappingOfCommandeShouldReturnRespectiveCommande() throws Exception {
        Long artID = (long) 1;
        when(commandeService.findById(commandeDto.getId())).thenReturn(commandeDto);
        mockMvc.perform(get("/casa-solaire/v1/commandes/" + artID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(commandeDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

}
