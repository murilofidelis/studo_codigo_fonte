import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ToastyService } from 'ng2-toasty';

import { Aluno } from './../../../model/aluno.model';
import { AlunoService } from './../../../service/aluno.service';
import { ErrorHandleService } from '../../../service/error-handle.service';
import { Matricula } from '../../../model/matricula.model';
import { Mensagem } from '../../../model/mensagens.model';

@Component({
  selector: 'app-matricula',
  templateUrl: './matricula.component.html',
  styleUrls: ['./matricula.component.css']
})
export class MatriculaComponent implements OnInit {

  aluno: Aluno;
  matricula: Matricula;
  matriculas: Matricula[];

  constructor(
    private alunoService: AlunoService,
    private activatedRoute: ActivatedRoute,
    private toasty: ToastyService,
    private errorHandle: ErrorHandleService
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
      console.log(this.matriculas);
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
        }).catch(erro => this.errorHandle.handle(erro));
    }
  }

}
