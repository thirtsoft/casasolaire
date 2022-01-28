package com.casaSolaire.services.impl;

import com.casaSolaire.dto.CommandeDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Commande;
import com.casaSolaire.repository.*;
import com.casaSolaire.services.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final LigneCommandeService ligneCommandeService;
    private final ArticleService articleService;
    private final ClientService clientService;
    private final UtilisateurService utilisateurService;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final ClientRepository clientRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ArticleRepository articleRepository;
    private final String status = "ENCOURS";
    double total = 0;
    Logger logger = LoggerFactory.getLogger(CommandeServiceImpl.class);

    @Autowired
    public CommandeServiceImpl(CommandeRepository commandeRepository,
                               LigneCommandeService ligneCommandeService,
                               ArticleService articleService,
                               ClientService clientService,
                               UtilisateurService utilisateurService,
                               LigneCommandeRepository ligneCommandeRepository,
                               ClientRepository clientRepository,
                               ArticleRepository articleRepository,
                               UtilisateurRepository utilisateurRepository
    ) {
        this.commandeRepository = commandeRepository;
        this.ligneCommandeService = ligneCommandeService;
        this.articleService = articleService;
        this.clientService = clientService;
        this.utilisateurService = utilisateurService;
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public CommandeDto save(CommandeDto commandeDto) {
        System.out.println("Initial Numero Commande " + commandeDto.getNumeroCommande());
        logger.info("CommandeDto {}", commandeDto);

      /*  ClientDto clientOptional = clientService.findById(commandeDto.getClientDto().getId());
        if (clientOptional == null) {
            throw new IllegalArgumentException("Vous devez selectionner un client");
        }
*/
       /* if (commandeDto.getLcomms() != null) {
            commandeDto.getLcomms().forEach(ligCmdClt -> {
                if (ligCmdClt.getArticleDto() != null) {
                    ArticleDto articleDto = articleService.findById(ligCmdClt.getArticleDto().getId());

                    if (articleDto == null) {
                        log.warn("L'article avec l'ID " + ligCmdClt.getArticleDto().getId() + " n'existe pas");
                    }

                } else {
                    log.warn("Impossible d'enregister une commande avec un aticle NULL");

                }

            });
        }*/

      /*  PurchaseDto purchaseDto = new PurchaseDto();
        commandeDto.setBillingAddressDto(purchaseDto.getBillingAddressDto());
        commandeDto.setShippingAddressDto(purchaseDto.getShippingAddressDto());*/

        Commande savedCmdClt = commandeRepository.save(CommandeDto.fromDtoToEntity(commandeDto));

     /*   if (commandeDto.getLcomms() != null) {
            commandeDto.getLcomms().forEach(ligCmdClt -> {
                        LigneCommande ligneCommande = LigneCommandeDto.fromDtoToEntity(ligCmdClt);
                        ligneCommande.setCommande(savedCmdClt);

                        ligneCommandeRepository.save(ligneCommande);

                        Optional<Article> articleDto = articleRepository.findById(ligCmdClt.getArticleDto().getId());

                        articleDto.get().setQuantity(articleDto.get().getQuantity() - ligneCommande.getQuantity());

                        ligneCommande.setNumero(savedCmdClt.getNumeroCommande());
                        ligneCommande.setPrice(articleDto.get().getPrice());

                        total += (ligneCommande.getQuantity() * ligneCommande.getPrice());
                    }

            );

        }*/

        savedCmdClt.setTotalCommande(total);
        savedCmdClt.setStatus(status);
        savedCmdClt.setDateCommande(new Date());

        return CommandeDto.fromEntityToDto(savedCmdClt);


    }

    @Override
    public CommandeDto saveWithAddresses(CommandeDto commandeDto) {
        System.out.println("Initial Numero Commande " + commandeDto.getNumeroCommande());
        logger.info("CommandeDto {}", commandeDto);

       /* ClientDto clientOptional = clientService.findById(commandeDto.getClientDto().getId());

        if (clientOptional == null) {
            //     log.warn("Client with ID {} was not found in the DB", commandeDto.getUtilisateurPOSTDto().getId());
            throw new IllegalArgumentException("Vous devez selectionner un client");
        }*/

       /* if (commandeDto.getLcomms() != null) {
            commandeDto.getLcomms().forEach(ligCmdClt -> {
                if (ligCmdClt.getArticleDto() != null) {
                    ArticleDto articleDto = articleService.findById(ligCmdClt.getArticleDto().getId());
                    //    Optional<Article> articleDto = articleRepository.findById(ligCmdClt.getArticleDto().getId());
                    if (articleDto == null) {
                        log.warn("L'article avec l'ID " + ligCmdClt.getArticleDto().getId() + " n'existe pas");
                    }

                } else {
                    log.warn("Impossible d'enregister une commande avec un aticle NULL");

                }

            });
        }*/

      /*  PurchaseDto purchaseDto = new PurchaseDto();
        commandeDto.setBillingAddressDto(purchaseDto.getBillingAddressDto());
        commandeDto.setShippingAddressDto(purchaseDto.getShippingAddressDto());*/

        Commande savedCmdClt = commandeRepository.save(CommandeDto.fromDtoToEntity(commandeDto));

       /* if (commandeDto.getLcomms() != null) {
            commandeDto.getLcomms().forEach(ligCmdClt -> {
                        LigneCommande ligneCommande = LigneCommandeDto.fromDtoToEntity(ligCmdClt);
                        ligneCommande.setCommande(savedCmdClt);
                        //        ligneCommandeService.save(LigneCommandeDto.fromEntityToDto(ligneCommande));
                        ligneCommandeRepository.save(ligneCommande);

                        Optional<Article> articleDto = articleRepository.findById(ligCmdClt.getArticleDto().getId());

                        articleDto.get().setQuantity(articleDto.get().getQuantity() - ligneCommande.getQuantity());

                        ligneCommande.setNumero(savedCmdClt.getNumeroCommande());
                        ligneCommande.setPrice(articleDto.get().getPrice());

                        total += (ligneCommande.getQuantity() * ligneCommande.getPrice());
                    }

            );

        }*/

        savedCmdClt.setTotalCommande(total);
        savedCmdClt.setStatus(status);
        savedCmdClt.setDateCommande(new Date());

        return CommandeDto.fromEntityToDto(savedCmdClt);
    }

    @Override
    public CommandeDto saveWithUtilisateur(Long userId, CommandeDto commandeDto) {
        return null;
    }

    @Override
    public CommandeDto update(Long comId, CommandeDto commandeDto) {
        if (!commandeRepository.existsById(comId)) {
            throw new ResourceNotFoundException("Commande not found");
        }

        Optional<Commande> commandeOptional = commandeRepository.findById(comId);

        if (!commandeOptional.isPresent()) {
            throw new ResourceNotFoundException("Commande not found");
        }

        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(commandeOptional.get());
        commandeDtoResult.setNumeroCommande(commandeDto.getNumeroCommande());
        commandeDtoResult.setTotalCommande(commandeDto.getTotalCommande());
        commandeDtoResult.setDateCommande(commandeDto.getDateCommande());
        commandeDtoResult.setLcomms(commandeDto.getLcomms());
        commandeDtoResult.setStatus(commandeDto.getStatus());

        return CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDtoResult)
                )
        );
    }

    @Override
    public CommandeDto updateStatusOfCommande(String status, String id) {

        Optional<Commande> commandeOptional = commandeRepository.findById(Long.valueOf(id));

        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(commandeOptional.get());

        commandeDtoResult.setStatus(status);

        return CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDtoResult)
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
                        "Aucnun Commande avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<CommandeDto> findAll() {
        return commandeRepository.findAll().stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDto> findByOrderByIdDesc() {
        return commandeRepository.findByOrderByIdDesc().stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDto> findListOrderByStatusPending() {
        return commandeRepository.findListOrderByStatusPending().stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDto> findListOrderByStatusPayed() {
        return commandeRepository.findListOrderByStatusPayed().stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDto> findListOrderByUserId(Long userId) {
        return null;
    }

    @Override
    public BigDecimal countNumberOfCommande() {
        return commandeRepository.countNumberOfCommande();
    }

    @Override
    public BigDecimal countNumberOfCommandesInMonth() {
        return commandeRepository.countNumberOfOrdersInMonth();
    }

    @Override
    public BigDecimal sumTotalOfCommandeByDay() {
        return commandeRepository.sumTotalOfCommandeByDay();
    }

    @Override
    public BigDecimal sumTotaleOfCommandeByMonth() {
        return commandeRepository.sumTotaleOfCommandeByMonth();
    }

    @Override
    public BigDecimal countNumberOfOrdersByStatusPending() {
        return commandeRepository.countNumberOfOrdersByStatusPending();
    }

    @Override
    public BigDecimal sumTotalOfCommandesByYear() {
        return commandeRepository.sumTotalOfCommandesByYear();
    }

    @Override
    public List<?> countNumberOfCommandeByDay() {
        return commandeRepository.countNumberOfCommandeByDay()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<?> countNumberTotalOfCommandeByMonth() {
        return commandeRepository.countNumberOfCommandeByMonth()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<?> sumTotalOfCommandeByMonth() {
        return commandeRepository.sumTotalOfCommandeByMonth()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<?> sumTotalOfOrdersByYears() {
        return commandeRepository.sumTotalOfCommandeByYears()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDto> findCommandesByUserOrderByIdDesc(Long userId) {
        return commandeRepository.ListCommandeByCustomerId(userId).stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

   /* @Override
    public List<CommandeDto> findCommandesByAddressLivraisonId(Long addLivraison) {
        return commandeRepository.ListCommandeByAddressLivraisonId(addLivraison).stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }*/

    @Override
    public List<CommandeDto> findCommandesByAddressAchatId(Long addAchat) {
        return commandeRepository.ListCommandeByAddressAchatId(addAchat).stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CommandeDto> findCommandeByUtilisateurPageables(Long userId, Pageable pageable) {
        return commandeRepository.findCommandeByUtilisateurPageables(userId, pageable)
                .map(CommandeDto::fromEntityToDto);
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
