package com.casaSolaire.services.impl;

import com.casaSolaire.dto.ScategoryDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Scategory;
import com.casaSolaire.repository.ScategoryRepository;
import com.casaSolaire.services.ScategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ScategoryServiceImpl implements ScategoryService {

    @Autowired
    private ScategoryRepository scategoryRepository;

    public ScategoryServiceImpl(ScategoryRepository scategoryRepository) {
        this.scategoryRepository = scategoryRepository;
    }

    @Override
    public ScategoryDto save(ScategoryDto scategoryDto) {

        return ScategoryDto.fromEntityToDto(
                scategoryRepository.save(
                        ScategoryDto.fromDtoToEntity(scategoryDto)
                )
        );
    }

    @Override
    public ScategoryDto findById(Long id) {
        if (id == null) {
            log.error("Scategorie Id is null");
            return null;
        }

        Optional<Scategory> scategorie = scategoryRepository.findById(id);

        return Optional.of(ScategoryDto.fromEntityToDto(scategorie.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun scategorie avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public ScategoryDto findByLibelle(String libelle) {
        if (!StringUtils.hasLength(libelle)) {
            log.error("Scategorie Libelle is null");
        }

        Optional<Scategory> scategorie = scategoryRepository.findScategorieByLibelle(libelle);

        return Optional.of(ScategoryDto.fromEntityToDto(scategorie.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun scategorie avec l'Id = " + libelle + "n'a été trouvé")
        );


    }

    @Override
    public List<ScategoryDto> findAll() {
        return scategoryRepository.findAll().stream()
                .map(ScategoryDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Scategorie Id is null");
            return;
        }
        scategoryRepository.deleteById(id);

    }
}
