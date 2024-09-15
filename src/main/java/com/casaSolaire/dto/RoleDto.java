package com.casaSolaire.dto;

import com.casaSolaire.enums.RoleName;
import com.casaSolaire.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    Long id;

    private RoleName name;

    public RoleDto(RoleName name) {
        this.name = name;
    }

    public static RoleDto formEntityToDto(Role role) {
        if (role == null) {
            return null;
        }

        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static Role fromDtoToEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }

        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());

        return role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

}
