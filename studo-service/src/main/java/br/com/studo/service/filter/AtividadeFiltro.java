package br.com.studo.service.filter;

import br.com.studo.domain.Atividade_;
import br.com.studo.domain.Disciplina_;
import br.com.studo.domain.Professor_;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class AtividadeFiltro implements FiltroPesquisa {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFim;

    private String titulo;

    private Long codigoDisciplina;

    private Long codigoProfessor;

    @Override
    public Specification filtro() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(getPredicates(root, criteriaBuilder).toArray(new Predicate[0]));
    }

    private List<Predicate> getPredicates(Root root, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(dataInicio)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Atividade_.dataCadastro), dataInicio));
        }
        if (Objects.nonNull(dataFim)) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Atividade_.dataCadastro), dataFim));
        }
        if (Objects.nonNull(titulo)) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(Atividade_.titulo)), "%" + titulo.toLowerCase() + "%"));
        }
        if (Objects.nonNull(codigoDisciplina)) {
            predicates.add(criteriaBuilder.equal(root.get(Atividade_.disciplina).get(Disciplina_.codigo), codigoDisciplina));
        }
        if (Objects.nonNull(codigoProfessor)) {
            predicates.add(criteriaBuilder.equal(root.get(Atividade_.professor).get(Professor_.codigo), codigoProfessor));
        }
        return predicates;
    }

}
