package com.springframework.rest.api.v1.model;

import lombok.Data;

import java.util.List;

@Data
public class VendorListDTO {

    private List<VendorDTO> vendors;
}
