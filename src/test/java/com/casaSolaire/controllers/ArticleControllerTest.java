package com.casaSolaire.controllers;


import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.dto.ScategoryDto;
import com.casaSolaire.services.ArticleService;
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
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;

    private ScategoryDto scategoryDto;
    private ArticleDto articleDto;

    private List<ArticleDto> articleDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        scategoryDto = new ScategoryDto();
        scategoryDto.setId(1L);
        scategoryDto.setCode("HP");
        scategoryDto.setLibelle("HP PRoBooks");
        articleDto = new ArticleDto(1L, "prod1", "prod1", 50, 12000, 5, "prod1", true, "photo", scategoryDto);

        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @After
    public void tearDown() {
        articleDto = null;
    }

    @Test
    public void PostMappingOfArticle() throws Exception {
        when(articleService.save(any())).thenReturn(articleDto);
        mockMvc.perform(post("/casa-solaire/v1/articles/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(articleDto)))
                .andExpect(status().isOk());
        verify(articleService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllArticles() throws Exception {
        when(articleService.findAll()).thenReturn(articleDtoList);
        mockMvc.perform(get("/casa-solaire/v1/articles/all").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(articleDto))).
                andDo(MockMvcResultHandlers.print());
        verify(articleService).findAll();
        verify(articleService, times(1)).findAll();
    }

    @Test
    public void GetMappingOfArticleShouldReturnRespectiveArticle() throws Exception {
        Long artID = (long) 1;
        when(articleService.findById(articleDto.getId())).thenReturn(articleDto);
        mockMvc.perform(get("/casa-solaire/v1/articles/" + artID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(articleDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

}
