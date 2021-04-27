package com.casaSolaire.dto;

import com.casaSolaire.enums.Status;
import com.casaSolaire.models.Article;
import com.casaSolaire.models.Client;
import com.casaSolaire.models.Commande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandeDto {

    private Long id;

    private String number;

    private double total;

    private LocalDate dateCommande;

    private Status status;

    private ClientDto clientDto;

    public static CommandeDto fromEntityToDto(Commande commande) {
        if (commande == null) {
            return null;
        }
        return CommandeDto.builder()
                .id(commande.getId())
                .number(commande.getNumber())
                .total(commande.getTotal())
                .status(commande.getStatus())
                .clientDto(ClientDto.fromEntityToDto(commande.getClient()))
                .build();
    }

    public static Commande fromDtoToEntity(CommandeDto commandeDto) {
        if (commandeDto == null) {
            return null;
        }
        Commande commande = new Commande();
        commande.setId(commandeDto.getId());
        commande.setTotal(commandeDto.getTotal());
        commande.setDateCommande(commandeDto.getDateCommande());
        commande.setStatus(commandeDto.getStatus());

        return commande;
    }
}
