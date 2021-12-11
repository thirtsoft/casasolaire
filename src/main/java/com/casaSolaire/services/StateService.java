package com.casaSolaire.services;

import com.casaSolaire.dto.StateDto;

import java.util.List;

public interface StateService {

    StateDto save(StateDto stateDto);

    StateDto update(Long id, StateDto stateDto);

    StateDto findById(Long id);

    List<StateDto> findAll();

    List<StateDto> findByOrderByIdDesc();

    List<StateDto> findAllStateByCountryCode(String code);

    void delete(Long id);

}
