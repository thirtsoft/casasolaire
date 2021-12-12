package com.casaSolaire.controllers;


import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.dto.FournisseurDto;
import com.casaSolaire.services.FournisseurService;
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
public class FournisseurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FournisseurService fournisseurService;

    @InjectMocks
    private FournisseurController fournisseurController;

    private ArticleDto articleDto;
    private FournisseurDto fournisseurDto;

    private List<FournisseurDto> fournisseurDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        articleDto = new ArticleDto();
        articleDto.setId(1L);
        articleDto.setReference("prod1");
        articleDto.setDesignation("prod1");
        fournisseurDto = new FournisseurDto(1L, "f1", "f1", "f1", "f1", "f1", articleDto);

        mockMvc = MockMvcBuilders.standaloneSetup(fournisseurController).build();
    }

    @After
    public void tearDown() {
        fournisseurDto = null;
    }

    @Test
    public void PostMappingOfFournisseur() throws Exception {
        when(fournisseurService.save(any())).thenReturn(fournisseurDto);
        mockMvc.perform(post("/casa-solaire/v1/fournisseurs/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fournisseurDto)))
                .andExpect(status().isOk());
        verify(fournisseurService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllFournisseurs() throws Exception {
        when(fournisseurService.findAll()).thenReturn(fournisseurDtoList);
        mockMvc.perform(get("/casa-solaire/v1/fournisseurs/all").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(fournisseurDto))).
                andDo(MockMvcResultHandlers.print());
        verify(fournisseurService).findAll();
        verify(fournisseurService, times(1)).findAll();
    }

    @Test
    public void GetMappingOfFournisseurShouldReturnRespectiveFournisseur() throws Exception {
        Long fourID = (long) 1;
        when(fournisseurService.findById(fournisseurDto.getId())).thenReturn(fournisseurDto);
        mockMvc.perform(get("/casa-solaire/v1/fournisseurs/" + fourID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(fournisseurDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

}
