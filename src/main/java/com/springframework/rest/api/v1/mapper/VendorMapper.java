package com.springframework.rest.api.v1.mapper;

import com.springframework.rest.api.v1.model.VendorDTO;
import com.springframework.rest.domain.Vendor;
import com.springframework.rest.service.VendorService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = VendorService.class)
//@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
    VendorDTO vendorToVendorDTO(Vendor vendor);
    Vendor vendorDtoToVendor(VendorDTO vendor);
}
