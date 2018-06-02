package br.com.studo.web.resource;

import br.com.studo.service.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("questao")
public class QuestaoResource {

    @Autowired
    private QuestaoService questaoService;
}
