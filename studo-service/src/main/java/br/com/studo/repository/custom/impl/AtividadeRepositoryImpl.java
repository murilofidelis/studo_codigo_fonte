package br.com.studo.repository.custom.impl;

import br.com.studo.annotation.ConsultaNativaResultadoMapeamento;
import br.com.studo.domain.dto.AtividadeConsultaDTO;
import br.com.studo.repository.custom.AtividadeRepositoryCustom;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AtividadeRepositoryImpl implements AtividadeRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<AtividadeConsultaDTO> buscaAtividades(Date dataInicio, Date dataFim, Long codProfessor, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT a.codigo, a.dsc_classificacao, a.dte_cadastro, a.descricao, a.titulo, d.descricao as disciplina FROM studo.tab_atividade a ");
        sql.append(getSql(dataInicio, dataFim, true));

        Query query = manager.createNativeQuery(sql.toString());
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());

        setParamtros(dataInicio, dataFim, codProfessor, query);

        return ConsultaNativaResultadoMapeamento.mapear(query.getResultList(), AtividadeConsultaDTO.class);
    }

    @Override
    public Long quantidade(Date dataInicio, Date dataFim, Long codProfessor) {
        StringBuilder sql = new StringBuilder("SELECT count(a)  FROM studo.tab_atividade a ");
        sql.append(getSql(dataInicio, dataFim, false));
        Query query = manager.createNativeQuery(sql.toString());
        setParamtros(dataInicio, dataFim, codProfessor, query);
        BigInteger qtd = (BigInteger) query.getSingleResult();
        return qtd.longValue();
    }

    private void setParamtros(Date dataInicio, Date dataFim, Long codProfessor, Query query) {
        for (Map.Entry<String, Object> entry : substituiParametros(dataInicio, dataFim, codProfessor).entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    private StringBuilder getSql(Date dataInicio, Date dataFim, boolean ordenar) {
        StringBuilder sql = new StringBuilder();
        sql.append(" INNER JOIN studo.tab_disciplina d on a.disciplina_codigo = d.codigo ");
        sql.append("WHERE 0 = 0 ");
        addWhere(sql, dataInicio, dataFim, ordenar);
        return sql;
    }

    private void addWhere(StringBuilder sql, Date dataInicio, Date dataFim, boolean ordenar) {
        sql.append(" AND a.professor_codigo =  :codProfessor ");
        if (Objects.nonNull(dataInicio)) {
            sql.append(" AND a.dte_cadastro >= :dataIncio ");
        }
        if (Objects.nonNull(dataFim)) {
            sql.append(" AND a.dte_cadastro <= :dataFim ");
        }
        if (ordenar) {
            sql.append(" ORDER BY a.codigo DESC ");
        }
    }

    private Map<String, Object> substituiParametros(Date dataInicio, Date dataFim, Long codProfessor) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("codProfessor", codProfessor);
        if (Objects.nonNull(dataInicio)) {
            parametros.put("dataIncio", dataInicio);
        }
        if (Objects.nonNull(dataFim)) {
            parametros.put("dataFim", dataFim);
        }
        return parametros;
    }
}
