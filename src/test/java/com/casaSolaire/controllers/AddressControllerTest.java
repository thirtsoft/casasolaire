package com.casaSolaire.controllers;


import com.casaSolaire.dto.AddressDto;
import com.casaSolaire.services.AddressService;
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
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AddressService addresseClientService;

    @InjectMocks
    private AddressController addresseClientController;

    private AddressDto addressClientDto;

    private List<AddressDto> addressClientDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        addressClientDto = new AddressDto();
        addressClientDto.setId(1L);
        addressClientDto.setCity("Dakar");

        mockMvc = MockMvcBuilders.standaloneSetup(addresseClientController).build();
    }

    @After
    public void tearDown() {
        addressClientDto = null;
    }

    @Test
    public void PostMappingOfAddressClient() throws Exception {
        when(addresseClientService.save(any())).thenReturn(addressClientDto);
        mockMvc.perform(post("/casa-solaire/v1/addresses/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(addressClientDto)))
                .andExpect(status().isOk());
        verify(addresseClientService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllAddressClients() throws Exception {
        when(addresseClientService.findAll()).thenReturn(addressClientDtoList);
        mockMvc.perform(get("/casa-solaire/v1/addresses/all").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(addressClientDto))).
                andDo(MockMvcResultHandlers.print());
        verify(addresseClientService).findAll();
        verify(addresseClientService, times(1)).findAll();
    }

    @Test
    public void GetMappingOfAddressClientShouldReturnRespectiveAddressClient() throws Exception {
        Long artID = (long) 1;
        when(addresseClientService.findById(addressClientDto.getId())).thenReturn(addressClientDto);
        mockMvc.perform(get("/casa-solaire/v1/addresses/" + artID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(addressClientDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

}
