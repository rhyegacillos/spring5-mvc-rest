package com.springframework.rest.service;

import com.springframework.rest.api.v1.mapper.VendorMapper;
import com.springframework.rest.api.v1.model.VendorDTO;
import com.springframework.rest.controller.v1.VendorController;
import com.springframework.rest.domain.Vendor;
import com.springframework.rest.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    @Mock
    private VendorRepository vendorRepository;

    private VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getVendorById() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Vendor 1");

        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(vendor1));

        VendorDTO vendorDTO = vendorService.getVendorById(vendor1.getId());

        assertEquals(vendor1.getName(), vendorDTO.getName());
    }

    @Test
    public void getAllVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Vendor 1");

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("Vendor 2");

        List<Vendor> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        assertEquals(vendors.size(), vendorDTOS.size());
    }

    @Test
    public void createNewVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor");

        Vendor vendorSaved = new Vendor();
        vendorSaved.setName(vendorDTO.getName());
        vendorSaved.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendorSaved);

        VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

        assertEquals(savedDTO.getName(), vendorDTO.getName());
        assertEquals(VendorController.BASE_URL + "/1", savedDTO.getVendorUrl());
    }

    @Test
    public void saveVendorByDTO() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor");

        Vendor vendor = new Vendor();
        vendor.setName(vendorDTO.getName());
        vendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO returnDTO = vendorService.saveVendorByDTO(1L, vendorDTO);

        assertEquals(returnDTO.getName(), vendor.getName());
        assertEquals(VendorController.BASE_URL + "/1", returnDTO.getVendorUrl());
    }

    @Test
    public void patchVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("vendor");

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("vendor1");

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.patchVendor(1L, vendorDTO);

        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());

        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void deleteVendorById() {
        Long id = 1L;

        vendorRepository.deleteById(id);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}