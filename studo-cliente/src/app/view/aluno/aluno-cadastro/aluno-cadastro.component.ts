import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { SelectItem } from 'primeng/components/common/api';
import { ToastyService } from 'ng2-toasty';

import { Mensagem } from './../../../model/mensagens.model';
import { Turma } from './../../../model/turma.model';
import { Matricula } from './../../../model/matricula.model';
import { Aluno } from './../../../model/aluno.model';
import { ValidadorCPF } from './../../../util/validator/cpf-validador';

import { ErrorHandleService } from './../../../service/error-handle.service';
import { AlunoService } from '../../../service/aluno.service';

@Component({
  selector: 'app-aluno-cadastro',
  templateUrl: './aluno-cadastro.component.html',
  styleUrls: ['./aluno-cadastro.component.css']
})
export class AlunoCadastroComponent implements OnInit {

  sexo: SelectItem[];
  alunoForm: FormGroup;
  aluno: Aluno = new Aluno();
  matricula: Matricula = new Matricula();
  matriculas: Matricula[];
  turma: Turma = new Turma();

  constructor(
    private alunoService: AlunoService,
    private formBuilder: FormBuilder,
    private toasty: ToastyService,
    private activatedRoute: ActivatedRoute,
    private route: Router,
    private errorHandle: ErrorHandleService
  ) { }

  ngOnInit() {
    this.alunoForm = this.formBuilder.group({
      'codigo': [null],
      'matricula': [null, Validators.required],
      'nome': [null, [Validators.required, Validators.maxLength(50)]],
      'dataNascimento': [null, Validators.required],
      'sexo': [null, Validators.required],

      'email': this.formBuilder.group({
        'codigo': [null],
        'dscEmail': [null, [Validators.required, Validators.email]],
      }),

    });
    this.iniciaSexo();
    this.matriculas = [];
  }

  iniciaSexo() {
    this.sexo = [];
    this.sexo.push({ label: 'Selecione...', value: null });
    this.sexo.push({ label: 'Masculino', value: 'MASCULINO' });
    this.sexo.push({ label: 'Feminino', value: 'FEMININO' });
  }

  salvar() {
    this.aluno = this.alunoForm.value;
    this.aluno.matriculas = this.matriculas;
    console.log(this.aluno);
    this.alunoService.salvar(this.aluno).then(() => {
      this.toasty.success(Mensagem.MENSAGEM_SALVO_SUCESSO);
    }).catch(erro => this.errorHandle.handle(erro));

  }

  alterouTurma(event) {
    this.turma = event;
    this.matricula.turma = event;
    this.matriculas.push(this.matricula);
  }
}
