package br.com.studo.repository;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.QAluno;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface AlunoRepository extends CrudRepository<Aluno, Long>, QueryDslPredicateExecutor<Aluno>, QuerydslBinderCustomizer<QAluno> {

    @Override
    default void customize(QuerydslBindings bindings, QAluno root) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    Page<Aluno> findByNomeStartingWithIgnoreCase(String nome, Pageable pageable);

    @Query("SELECT count(*) FROM Aluno")
    Integer quantidade();

}
