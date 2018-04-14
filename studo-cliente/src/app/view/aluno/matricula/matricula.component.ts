import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ToastyService } from 'ng2-toasty';

import { ConfirmationService } from 'primeng/components/common/api';

import { Mensagem } from '../../../util/mensagens.util';

import { Aluno } from './../../../model/aluno.model';
import { AlunoService } from './../../../service/aluno.service';
import { ErrorHandleService } from '../../../service/error-handle.service';
import { Matricula } from '../../../model/matricula.model';

@Component({
  selector: 'app-matricula',
  templateUrl: './matricula.component.html',
  styleUrls: ['./matricula.component.css'],
  providers: [ConfirmationService]
})
export class MatriculaComponent implements OnInit {

  aluno: Aluno;
  matricula: Matricula;
  matriculas: Matricula[];

  constructor(
    private alunoService: AlunoService,
    private activatedRoute: ActivatedRoute,
    private toasty: ToastyService,
    private errorHandle: ErrorHandleService,
    private confirmartion: ConfirmationService
  ) { }

  ngOnInit() {
    const codigoAluno = this.activatedRoute.snapshot.params['codigo'];
    this.aluno = new Aluno();
    this.matricula = new Matricula();

    if (codigoAluno) {
      this.carregarAluno(codigoAluno);
      this.buscaMatriculasPorAluno(codigoAluno);
    }
  }

  carregarAluno(codigo: number) {
    this.alunoService.buscaPorCodigo(codigo)
      .then(aluno => {
        this.aluno = aluno;
      }).catch(erro => this.errorHandle.handle(erro));
  }

  buscaMatriculasPorAluno(codigo: number) {
    this.alunoService.buscaMatriculasPorAluno(codigo).then(matriculas => {
      this.matriculas = matriculas;
    }).catch(erro => this.errorHandle.handle(erro));
  }

  atualizaTurma(event) {
    this.matricula.turma = event;
  }

  salvarMatricula() {
    this.matricula.aluno = this.aluno;
    if (this.matricula.aluno.codigo && this.matricula.turma.codigo) {
      this.alunoService.salvarMatricula(this.matricula)
        .then(() => {
          this.toasty.success(Mensagem.MENSAGEM_SALVO_SUCESSO);
          this.buscaMatriculasPorAluno(this.aluno.codigo);
        }).catch(erro => this.errorHandle.handle(erro));
    }
  }

  excluirMatricula(matricula: Matricula) {
    this.alunoService.excluirMatricula(matricula.codigo)
      .then(() => {
        this.toasty.success(Mensagem.MENSAGEM_EXCLUIDO_SUCESSO);
        this.buscaMatriculasPorAluno(this.aluno.codigo);
      }).catch(erro => this.errorHandle.handle(erro));
  }

  confirmarExclusaoMatricula(matricula: Matricula) {
    this.confirmartion.confirm({
      message: `Deseja excluir esta matrícula do aluno: ${matricula.aluno.nome} ?`,
      header: 'Excluir matrícula',
      icon: 'fa fa-question-circle',
      accept: () => {
        this.excluirMatricula(matricula);
      },
      reject: () => { }
    });
  }

}
