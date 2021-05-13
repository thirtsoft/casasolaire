package com.casaSolaire.dto;

import com.casaSolaire.models.Scategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScategoryDto {

    private long id;

    private String code;

    private String libelle;

    private CategoryDto categoryDto;

    public static ScategoryDto fromEntityToDto(Scategory scategory) {
        if (scategory == null) {
            return null;
        }

        return ScategoryDto.builder()
                .id(scategory.getId())
                .code(scategory.getCode())
                .libelle(scategory.getLibelle())
                .categoryDto(CategoryDto.fromEntityToDto(scategory.getCategory()))
                .build();
    }

    public static Scategory fromDtoToEntity(ScategoryDto scategoryDto) {
        if (scategoryDto == null) {
            return null;
        }

        Scategory scategory = new Scategory();
        scategory.setId(scategoryDto.getId());
        scategory.setCode(scategoryDto.getCode());
        scategory.setLibelle(scategoryDto.getLibelle());
        scategory.setCategory(CategoryDto.fromDtoToEntity(scategoryDto.getCategoryDto()));

        return scategory;
    }

}
