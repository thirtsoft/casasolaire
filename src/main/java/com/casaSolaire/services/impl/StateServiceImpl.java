package com.casaSolaire.services.impl;

import com.casaSolaire.dto.StateDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.State;
import com.casaSolaire.repository.StateRepository;
import com.casaSolaire.services.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class StateServiceImpl implements StateService {

    @Autowired
    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }


    @Override
    public StateDto save(StateDto stateDto) {

        return StateDto.fromEntityToDto(
                stateRepository.save(
                        StateDto.fromDtoToEntity(stateDto)
                )
        );
    }

    @Override
    public StateDto update(Long id, StateDto stateDto) {
        if (!stateRepository.existsById(id)) {
            throw new ResourceNotFoundException("State not found");
        }

        Optional<State> stateOptional = stateRepository.findById(id);

        if (!stateOptional.isPresent()) {
            throw new ResourceNotFoundException("State not found");
        }

        StateDto stateDtoResult = StateDto.fromEntityToDto(stateOptional.get());

        stateDtoResult.setName(stateDto.getName());
        stateDtoResult.setCountryDto(stateDto.getCountryDto());

        return StateDto.fromEntityToDto(
                stateRepository.save(
                        StateDto.fromDtoToEntity(stateDtoResult)
                )
        );
    }

    @Override
    public StateDto findById(Long id) {
        if (id == null) {
            log.error("Country Id is null");
            return null;
        }

        Optional<State> stateOptional = stateRepository.findById(id);

        return Optional.of(StateDto.fromEntityToDto(stateOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun State avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<StateDto> findAll() {
        return stateRepository.findAll().stream()
                .map(StateDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StateDto> findByOrderByIdDesc() {
        return stateRepository.findByOrderByIdDesc().stream()
                .map(StateDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StateDto> findAllStateByCountryCode(String code) {
        if (code == null) {
            log.error("State Country is null");
        }
        return stateRepository.findByCountryCode(code).stream()
                .map(StateDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Country Id is null");
        }
        stateRepository.deleteById(id);
    }

}
