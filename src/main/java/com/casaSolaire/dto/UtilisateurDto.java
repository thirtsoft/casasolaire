package com.casaSolaire.dto;

import com.casaSolaire.models.Category;
import com.casaSolaire.models.Utilisateur;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {

    private Long id;

    private String name;

    private String username;

    private String mobile;

    private String password;

    private String address;

    private boolean isActive;

    public static UtilisateurDto fromEntityToDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .name(utilisateur.getName())
                .username(utilisateur.getName())
                .mobile(utilisateur.getMobile())
                .password(utilisateur.getPassword())
                .address(utilisateur.getAddress())
                .isActive(utilisateur.isActive())
                .build();
    }

    public static Utilisateur fromDtoToEntity(UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setName(utilisateurDto.getName());
        utilisateur.setUsername(utilisateurDto.getUsername());
        utilisateur.setMobile(utilisateurDto.getMobile());
        utilisateur.setPassword(utilisateurDto.getPassword());
        utilisateur.setAddress(utilisateurDto.getAddress());
        utilisateur.setActive(utilisateurDto.isActive());

        return utilisateur;
    }
}
