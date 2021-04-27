package com.casaSolaire.services.impl;

import com.casaSolaire.dto.CommandeDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Commande;
import com.casaSolaire.repository.CommandeRepository;
import com.casaSolaire.services.CommandeService;
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
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private final CommandeRepository commandeRepository;

    public CommandeServiceImpl(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }


    @Override
    public CommandeDto save(CommandeDto commandeDto) {
        return CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto)
                )
        );

    }

    @Override
    public CommandeDto findById(Long id) {
        if (id == null) {
            log.error("Commande Id is null");
            return null;
        }

        Optional<Commande> commande = commandeRepository.findById(id);

        return Optional.of(CommandeDto.fromEntityToDto(commande.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Not commande with l'Id = " + id + "n'a été found")
        );
    }

    @Override
    public List<CommandeDto> findAll() {
        return commandeRepository.findAll().stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Commande Id is null");
            return;
        }
        commandeRepository.deleteById(id);

    }
}
