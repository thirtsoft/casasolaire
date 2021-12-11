package com.casaSolaire.services;

import com.casaSolaire.dto.HistoriqueLoginDto;

import java.math.BigDecimal;
import java.util.List;

public interface HistoriqueLoginService {

    HistoriqueLoginDto saveHistoriqueLogin(HistoriqueLoginDto historiqueLoginDto);

    HistoriqueLoginDto saveHistoriqueLoginWithConnectedUser(HistoriqueLoginDto historiqueLoginDto, Long userId);

    HistoriqueLoginDto findHistoriqueLoginById(Long id);

    List<HistoriqueLoginDto> findAllHistoriqueLogins();

    List<HistoriqueLoginDto> findAllHistoriqueLoginsOrderDesc();

    BigDecimal countNumberOfHistoriqueLogins();

    void deleteHistoriqueLogin(Long id);

}
