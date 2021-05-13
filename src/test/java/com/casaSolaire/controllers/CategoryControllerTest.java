package com.casaSolaire.controllers;


import com.casaSolaire.dto.CategoryDto;
import com.casaSolaire.services.CategoryService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private CategoryDto categoryDto;
    private List<CategoryDto> categoryDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        categoryDto = new CategoryDto(1L, "PC", "HP ProBooks");
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @After
    public void tearDown() {
        categoryDto = null;
    }

    @Test
    public void PostMappingOfCategory() throws Exception {
        when(categoryService.save(any())).thenReturn(categoryDto);
        mockMvc.perform(post("/casa-solaire/v1/categories/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(categoryDto)))
                .andExpect(status().isOk());
        verify(categoryService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllCategories() throws Exception {
        when(categoryService.findAll()).thenReturn(categoryDtoList);
        mockMvc.perform(get("/casa-solaire/v1/categories/all").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(categoryDto))).
                andDo(MockMvcResultHandlers.print());
        verify(categoryService).findAll();
        verify(categoryService, times(1)).findAll();
    }

    @Test
    public void GetMappingOfCategoryShouldReturnRespectiveCategory() throws Exception {
        Long catID = (long) 1;
        when(categoryService.findById(categoryDto.getId())).thenReturn(categoryDto);
        mockMvc.perform(get("/casa-solaire/v1/categories/" + catID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(categoryDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void GetMappingOfCategoryByDesignationShouldReturnRespectiveCategory() throws Exception {
        String designation = "HP ProBooks";
        when(categoryService.findById(categoryDto.getId())).thenReturn(categoryDto);
        mockMvc.perform(get("/casa-solaire/v1/categories/searchbyDesignation/" + designation).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(categoryDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }




}
