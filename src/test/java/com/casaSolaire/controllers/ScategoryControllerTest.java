package com.casaSolaire.controllers;


import com.casaSolaire.dto.CategoryDto;
import com.casaSolaire.dto.ScategoryDto;
import com.casaSolaire.services.CategoryService;
import com.casaSolaire.services.ScategoryService;
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
public class ScategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    // @MockBean

    @Mock
    private ScategoryService scategoryService;

    @InjectMocks
    private ScategoryController scategoryController;

    private CategoryDto categoryDto;
    private ScategoryDto scategoryDto;

    private List<ScategoryDto> scategoryDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        categoryDto = new CategoryDto(1L, "PC", "PC");
        scategoryDto = new ScategoryDto(1L, "HP", "HP ProBooks", categoryDto);
        mockMvc = MockMvcBuilders.standaloneSetup(scategoryController).build();
    }

    @After
    public void tearDown() {
        scategoryDto = null;
    }

    @Test
    public void PostMappingOfScategory() throws Exception {
        when(scategoryService.save(any())).thenReturn(scategoryDto);
        mockMvc.perform(post("/casa-solaire/v1/scategories/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(scategoryDto)))
                .andExpect(status().isOk());
        verify(scategoryService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllScategories() throws Exception {
        when(scategoryService.findAll()).thenReturn(scategoryDtoList);
        mockMvc.perform(get("/casa-solaire/v1/scategories/all").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(scategoryDto))).
                andDo(MockMvcResultHandlers.print());
        verify(scategoryService).findAll();
        verify(scategoryService, times(1)).findAll();
    }

    @Test
    public void GetMappingOfSCategoryShouldReturnRespectiveSCategory() throws Exception {
        Long scatID = (long) 1;
        when(scategoryService.findById(scategoryDto.getId())).thenReturn(scategoryDto);
        mockMvc.perform(get("/casa-solaire/v1/scategories/" + scatID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(scategoryDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void GetMappingOfSCategoryByLibelleShouldReturnRespectiveSCategory() throws Exception {
        String libelle = "HP ProBooks";
        when(scategoryService.findById(scategoryDto.getId())).thenReturn(scategoryDto);
        mockMvc.perform(get("/casa-solaire/v1/scategories/searchbyLibelle/" + libelle).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(scategoryDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }



}
