package com.casaSolaire.dto;

import com.casaSolaire.models.Client;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ClientDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    public ClientDto(Long id, String firstName,
                     String lastName, String email, String mobile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
    }

   /*public void add(CommandeDto commandeDto) {
        if (commandeDto != null) {
            if (commandeDtoList == null) {
                commandeDtoList = new ArrayList<>();
            }
            commandeDtoList.add(commandeDto);
            commandeDto.setClientDto(this);
        }
    }*/

    public static ClientDto fromEntityToDto(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .mobile(client.getMobile())
                .build();
    }

    public static Client fromDtoToEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setMobile(clientDto.getMobile());

        return client;
    }


}
