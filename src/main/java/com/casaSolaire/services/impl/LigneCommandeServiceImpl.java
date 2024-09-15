package com.casaSolaire.services.impl;

import com.casaSolaire.dto.LigneCommandeDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.LigneCommande;
import com.casaSolaire.repository.LigneCommandeRepository;
import com.casaSolaire.services.LigneCommandeService;
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
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    public LigneCommandeServiceImpl(LigneCommandeRepository ligneCommandeRepository) {
        this.ligneCommandeRepository = ligneCommandeRepository;
    }

    @Override
    public LigneCommandeDto save(LigneCommandeDto ligneCommandeDto) {
        return LigneCommandeDto.fromEntityToDto(
                ligneCommandeRepository.save(
                        LigneCommandeDto.fromDtoToEntity(ligneCommandeDto)
                )
        );
    }

    @Override
    public LigneCommandeDto findById(Long id) {
        if (id == null) {
            log.error("LigneCommande Id is null");
            return null;
        }

        Optional<LigneCommande> ligneCommande = ligneCommandeRepository.findById(id);

        return Optional.of(LigneCommandeDto.fromEntityToDto(ligneCommande.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun LigneCommande avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<LigneCommandeDto> findAll() {
        return ligneCommandeRepository.findAll().stream()
                .map(LigneCommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeDto> findByOrderByIdDesc() {
        return ligneCommandeRepository.findByOrderByIdDesc().stream()
                .map(LigneCommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeDto> findArticlesGroupByProductIdOrderByCreatedDateDesc() {
        return ligneCommandeRepository.findArticlesGroupByProductId().stream()
                .map(LigneCommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeDto> findListLigneCommandeByCommandeId(Long comId) {
        return ligneCommandeRepository.ListLigneCommandeByCommandeId(comId).stream()
                .map(LigneCommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeDto> findTop200LigneCommandeOrderByIdDesc() {
        return ligneCommandeRepository.findTop200ByOrderByIdDesc().stream()
                .map(LigneCommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("LigneCommande Id is null");
            return;
        }
        ligneCommandeRepository.deleteById(id);
    }
}
