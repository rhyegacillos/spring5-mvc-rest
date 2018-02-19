package com.springframework.rest.controller.v1;

import com.springframework.rest.api.v1.model.VendorDTO;
import com.springframework.rest.service.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    public void getAllVendors() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("vendor");
        vendorDTO.setVendorUrl(vendorController.BASE_URL + "/1");

        VendorDTO vendorDTO2 = new VendorDTO();
        vendorDTO2.setName("vendor");
        vendorDTO2.setVendorUrl(vendorController.BASE_URL + "/2");

        List<VendorDTO> vendors = Arrays.asList(vendorDTO, vendorDTO2);

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc.perform(get(vendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));

    }

    @Test
    public void getVendor() {
    }

    @Test
    public void createVendor() {
    }

    @Test
    public void updateVendor() {
    }

    @Test
    public void patchVendor() {
    }

    @Test
    public void deleteVendor() {
    }
}