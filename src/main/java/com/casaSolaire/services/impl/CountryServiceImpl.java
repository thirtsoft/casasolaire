package com.casaSolaire.services.impl;

import com.casaSolaire.dto.CountryDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Country;
import com.casaSolaire.repository.CountryRepository;
import com.casaSolaire.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CountryServiceImpl implements CountryService {

    @Autowired
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public CountryDto save(CountryDto countryDto) {

        return CountryDto.fromEntityToDto(
                countryRepository.save(
                        CountryDto.fromDtoToEntity(countryDto)
                )
        );
    }

    @Override
    public CountryDto update(Long id, CountryDto countryDto) {
        if (!countryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Country not found");
        }

        Optional<Country> countryOptional = countryRepository.findById(id);

        if (!countryOptional.isPresent()) {
            throw new ResourceNotFoundException("Country not found");
        }

        CountryDto countryResult = CountryDto.fromEntityToDto(countryOptional.get());

        countryResult.setName(countryDto.getName());

        return CountryDto.fromEntityToDto(
                countryRepository.save(
                        CountryDto.fromDtoToEntity(countryResult)
                )
        );
    }

    @Override
    public BigDecimal countNumberOfCountry() {
        return countryRepository.countNumberOfCountry();
    }

    @Override
    public CountryDto findById(Long id) {
        if (id == null) {
            log.error("Country Id is null");
            return null;
        }

        Optional<Country> countryOptional = countryRepository.findById(id);

        return Optional.of(CountryDto.fromEntityToDto(countryOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Country avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<CountryDto> findAll() {
        return countryRepository.findAll().stream()
                .map(CountryDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CountryDto> findByOrderByIdDesc() {
        return countryRepository.findByOrderByIdDesc().stream()
                .map(CountryDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Country Id is null");
        }
        countryRepository.deleteById(id);

    }
}
