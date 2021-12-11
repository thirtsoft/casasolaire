package com.casaSolaire.dto;

import com.casaSolaire.models.Commande;
import com.casaSolaire.models.LigneCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandeDto {

    private Long id;

    private Long numeroCommande;

    private double totalCommande;

    private int totalQuantity;

    private String orderTrackingNumber;

    private String status;

    private String sessionId;

    private Date dateCommande;

    private ClientDto clientDto;

    private UtilisateurDto utilisateurDto;

    private AddressDto shippingAddressDto;

    private AddressDto billingAddressDto;

    private List<LigneCommande> lcomms = new ArrayList<>();


    public static CommandeDto fromEntityToDto(Commande commande) {
        if (commande == null) {
            return null;
        }

        return CommandeDto.builder()
                .id(commande.getId())
                .numeroCommande(commande.getNumeroCommande())
                .totalCommande(commande.getTotalCommande())
                .totalQuantity(commande.getTotalQuantity())
                .dateCommande(commande.getDateCommande())
                .status(commande.getStatus())
                .orderTrackingNumber(commande.getOrderTrackingNumber())
                .clientDto(ClientDto.fromEntityToDto(commande.getClient()))
                .utilisateurDto(UtilisateurDto.fromEntityToDto(commande.getUtilisateur()))
                .billingAddressDto(AddressDto.fromEntityToDto(commande.getBillingAddress()))
                .shippingAddressDto(AddressDto.fromEntityToDto(commande.getShippingAddress()))
                .lcomms(commande.getLcomms())
                .build();

    }

    public static Commande fromDtoToEntity(CommandeDto commandeDto) {
        if (commandeDto == null) {
            return null;
        }

        Commande commande = new Commande();
        commande.setId(commandeDto.getId());
        commande.setNumeroCommande(commandeDto.getNumeroCommande());
        commande.setDateCommande(commandeDto.getDateCommande());
        commande.setOrderTrackingNumber(commandeDto.getOrderTrackingNumber());
        commande.setTotalCommande(commandeDto.getTotalCommande());
        commande.setTotalQuantity(commandeDto.getTotalQuantity());
        commande.setStatus(commandeDto.getStatus());
        commande.setDateCommande(commandeDto.getDateCommande());
        commande.setClient(ClientDto.fromDtoToEntity(commandeDto.getClientDto()));
        commande.setUtilisateur(UtilisateurDto.fromDtoToEntity(commandeDto.getUtilisateurDto()));
        commande.setBillingAddress(AddressDto.fromDtoToEntity(commandeDto.getBillingAddressDto()));
        commande.setShippingAddress(AddressDto.fromDtoToEntity(commandeDto.getShippingAddressDto()));
        commande.setLcomms(commandeDto.getLcomms());

        return commande;
    }


}
