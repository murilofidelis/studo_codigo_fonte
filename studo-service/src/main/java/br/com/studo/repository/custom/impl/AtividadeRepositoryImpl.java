package br.com.studo.repository.custom.impl;

import br.com.studo.domain.dto.AtividadeDTO;
import br.com.studo.domain.dto.DisciplinaDTO;
import br.com.studo.repository.custom.AtividadeRepositoryCustom;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AtividadeRepositoryImpl implements AtividadeRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<AtividadeDTO> buscaAtividades(LocalDateTime dataInicio, LocalDateTime dataFim, Long codProfessor, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT a.codigo, a.dsc_classificacao, a.dte_cadastro, a.descricao, a.titulo, d.codigo as cod_disciplina, d.descricao as disciplina FROM studo.tab_atividade a ");
        sql.append(getSql(dataInicio, dataFim, codProfessor));

        Query query = manager.createNativeQuery(sql.toString());
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());

        for (Map.Entry<String, Object> entry : substituiParametros(dataInicio, dataFim, codProfessor).entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<Object[]> atividades = query.getResultList();
        return montarResultado(atividades);
    }


    @Override
    public Long quantidade(LocalDateTime dataInicio, LocalDateTime dataFim, Long codProfessor, Pageable pageable) {

        StringBuilder sql = new StringBuilder("SELECT count(*)  FROM studo.tab_atividade a ");
        sql.append(getSql(dataInicio, dataFim, codProfessor));
        Query query = manager.createNativeQuery(sql.toString());
        for (Map.Entry<String, Object> entry : substituiParametros(dataInicio, dataFim, codProfessor).entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        BigInteger qtd = (BigInteger) query.getSingleResult();
        return qtd.longValue();
    }


    private List<AtividadeDTO> montarResultado(List<Object[]> atividades) {
        List<AtividadeDTO> listaAtividades = new ArrayList<>();
        if (!atividades.isEmpty()) {
            atividades.forEach(atividade -> {
                AtividadeDTO dto = new AtividadeDTO();
                dto.setCodigo(Long.parseLong(atividade[0].toString()));
                dto.setClassificacao(atividade[1].toString());
                dto.setDataCadastro(LocalDate.parse(atividade[2].toString()));
                dto.setDescricao(atividade[3].toString());
                dto.setTitulo(atividade[4].toString());
                DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
                disciplinaDTO.setCodigo(Long.parseLong(atividade[5].toString()));
                disciplinaDTO.setDescricao(atividade[6].toString());
                dto.setDisciplina(disciplinaDTO);
                listaAtividades.add(dto);
            });
        }
        return listaAtividades;
    }


    private StringBuilder getSql(LocalDateTime dataInicio, LocalDateTime dataFim, Long codProfessor) {
        StringBuilder sql = new StringBuilder();
        sql.append(" INNER JOIN studo.tab_disciplina d on a.disciplina_codigo = d.codigo ");
        sql.append("WHERE 0 = 0 ");
        addWhere(dataInicio, dataFim, codProfessor, sql);
        return sql;
    }

    private void addWhere(LocalDateTime dataInicio, LocalDateTime dataFim, Long codProfessor, StringBuilder sql) {
        sql.append(" AND a.professor_codigo =  :codProfessor ");
        if (Objects.nonNull(dataInicio) && Objects.nonNull(dataFim)) {
            sql.append(" AND a.dte_cadastro BETWEEN :dataIncio AND :dataFim ");
        }
    }

    private Map<String, Object> substituiParametros(LocalDateTime dataInicio, LocalDateTime dataFim, Long codProfessor) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("codProfessor", codProfessor);
        if (Objects.nonNull(dataInicio) && Objects.nonNull(dataFim)) {
            parametros.put("dataIncio", dataInicio);
            parametros.put("dataFim", dataFim);
        }
        return parametros;
    }
}
