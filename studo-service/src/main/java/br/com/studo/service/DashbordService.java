package br.com.studo.service;

import br.com.studo.domain.dto.DashbordDTO;

import javax.servlet.http.HttpServletResponse;

public interface DashbordService {

    DashbordDTO montarDashBord();

    void gerarRelatotio(HttpServletResponse response);
}
