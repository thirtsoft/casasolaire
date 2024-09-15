package com.casaSolaire.dto;

import com.casaSolaire.models.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StateDto {

    private Long id;

    private String name;

    private CountryDto countryDto;

    public static StateDto fromEntityToDto(State state) {
        if (state == null) {
            return null;
        }

        return StateDto.builder()
                .id(state.getId())
                .name(state.getName())
                .countryDto(CountryDto.fromEntityToDto(state.getCountry()))
                .build();
    }

    public static State fromDtoToEntity(StateDto stateDto) {
        if (stateDto == null) {
            return null;
        }

        State state = new State();
        state.setId(stateDto.getId());
        state.setName(stateDto.getName());
        state.setCountry(CountryDto.fromDtoToEntity(stateDto.getCountryDto()));

        return state;
    }

}
