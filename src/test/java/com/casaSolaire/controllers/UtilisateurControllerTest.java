package com.casaSolaire.controllers;


import com.casaSolaire.dto.UtilisateurDto;
import com.casaSolaire.services.UtilisateurService;
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
public class UtilisateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    private UtilisateurDto utilisateurDto;
    private List<UtilisateurDto> utilisateurDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(1L);
        utilisateurDto.setName("user1");
        utilisateurDto.setUsername("thir");

        mockMvc = MockMvcBuilders.standaloneSetup(utilisateurController).build();
    }

    @After
    public void tearDown() {
        utilisateurDto = null;
    }

    @Test
    public void PostMappingOfUtilisateur() throws Exception {
        when(utilisateurService.save(any())).thenReturn(utilisateurDto);
        mockMvc.perform(post("/casa-solaire/v1/utilisateurs/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(utilisateurDto)))
                .andExpect(status().isOk());
        verify(utilisateurService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllUtilisateurs() throws Exception {
        when(utilisateurService.findAll()).thenReturn(utilisateurDtoList);
        mockMvc.perform(get("/casa-solaire/v1/utilisateurs/all").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(utilisateurDto))).
                andDo(MockMvcResultHandlers.print());
        verify(utilisateurService).findAll();
        verify(utilisateurService, times(1)).findAll();
    }

    @Test
    public void GetMappingOfUtilisateurShouldReturnRespectiveUtilisateur() throws Exception {
        Long userID = (long) 1;
        when(utilisateurService.findById(utilisateurDto.getId())).thenReturn(utilisateurDto);
        mockMvc.perform(get("/casa-solaire/v1/utilisateurs/" + userID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(utilisateurDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

}
