package br.com.studo.service.impl;

import br.com.studo.domain.LogErro;
import br.com.studo.repository.LogErroRepository;
import br.com.studo.security.SecurityUtil;
import br.com.studo.service.LogErroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class LogErroServiceImpl implements LogErroService {

    @Autowired
    private LogErroRepository logErroRepository;

    @Override
    public void salvarLog(String log) {

        LogErro logErro = new LogErro();
        logErro.setData(LocalDateTime.now());
        logErro.setUsuarioLogado(SecurityUtil.getUsuarioLogado());
        logErro.setStackTrace(log);
        logErroRepository.save(logErro);
    }
}
