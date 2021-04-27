package com.casaSolaire.dto;

import com.casaSolaire.models.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;

    private String reference;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    private String address;

    public static ClientDto fromEntityToDto(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .reference(client.getReference())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .mobile(client.getMobile())
                .address(client.getAddress())
                .build();
    }

    public static Client fromDtoToEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setReference(clientDto.getReference());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setMobile(clientDto.getMobile());
        client.setAddress(clientDto.getAddress());

        return client;
    }
}
