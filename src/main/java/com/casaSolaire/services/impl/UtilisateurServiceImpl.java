package com.casaSolaire.services.impl;

import com.casaSolaire.dto.UtilisateurDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Utilisateur;
import com.casaSolaire.repository.UtilisateurRepository;
import com.casaSolaire.services.UtilisateurService;
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
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }


    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        return UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );

    }

    @Override
    public UtilisateurDto findById(Long id) {
        if (id == null) {
            log.error("Utilisateur Id is null");
            return null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);

        return Optional.of(UtilisateurDto.fromEntityToDto(utilisateur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Not utilisateur with l'Id = " + id + "n'a été found")
        );
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Utilisateur Id is null");
            return;
        }
        utilisateurRepository.deleteById(id);

    }
}
