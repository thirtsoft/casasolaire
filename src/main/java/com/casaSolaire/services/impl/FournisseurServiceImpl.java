package com.casaSolaire.services.impl;

import com.casaSolaire.dto.FournisseurDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Fournisseur;
import com.casaSolaire.repository.FournisseurRepository;
import com.casaSolaire.services.FournisseurService;
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
public class FournisseurServiceImpl implements FournisseurService {

    @Autowired
    private final FournisseurRepository fournisseurRepository;

    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }


    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        return FournisseurDto.fromEntityToDto(
                fournisseurRepository.save(
                        FournisseurDto.fromDtoToEntity(fournisseurDto)
                )
        );

    }

    @Override
    public FournisseurDto update(Long id, FournisseurDto fournisseurDto) {
        if (!fournisseurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fournisseur not found");
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);

        if (!fournisseur.isPresent()) {
            throw new ResourceNotFoundException("Fournisseur not found");
        }

        FournisseurDto fournisseurDTOResult = FournisseurDto.fromEntityToDto(fournisseur.get());
        fournisseurDTOResult.setReference(fournisseurDto.getReference());
        fournisseurDTOResult.setFirstName(fournisseurDto.getFirstName());
        fournisseurDTOResult.setLastName(fournisseurDto.getLastName());
        fournisseurDTOResult.setTown(fournisseurDto.getTown());
        fournisseurDTOResult.setTelephone(fournisseurDto.getTelephone());
        fournisseurDTOResult.setEmail(fournisseurDto.getEmail());
        fournisseurDTOResult.setCity(fournisseurDto.getCity());
        fournisseurDTOResult.setRue(fournisseurDto.getRue());
        fournisseurDTOResult.setArticleDto(fournisseurDto.getArticleDto());

        return FournisseurDto.fromEntityToDto(
                fournisseurRepository.save(
                        FournisseurDto.fromDtoToEntity(fournisseurDTOResult)
                )
        );
    }

    @Override
    public FournisseurDto findById(Long id) {
        if (id == null) {
            log.error("Article Id is null");
            return null;
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);

        return Optional.of(FournisseurDto.fromEntityToDto(fournisseur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Not fournisseur with l'Id = " + id + "n'a été found")
        );
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Fournisseur Id is null");
            return;
        }
        fournisseurRepository.deleteById(id);

    }
}
