package com.casaSolaire.dto;

import com.casaSolaire.models.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;

    private String code;

    private String city;

    private String rue;

    private String town;

    private String country;

    public static AddressDto fromEntityToDto(Address address) {
        if (address == null) {
            return null;
        }
        return AddressDto.builder()
                .id(address.getId())
                .code(address.getCode())
                .city(address.getCity())
                .rue(address.getRue())
                .town(address.getTown())
                .country(address.getCountry())
                .build();
    }

    public static Address fromDtoToEntity(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setCode(addressDto.getCode());
        address.setCity(addressDto.getCity());
        address.setRue(addressDto.getRue());
        address.setTown(addressDto.getTown());
        address.setCountry(addressDto.getCountry());

        return address;
    }
}
