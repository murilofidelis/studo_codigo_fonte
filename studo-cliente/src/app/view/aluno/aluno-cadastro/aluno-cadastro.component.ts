import { Matricula } from './../../../model/matricula.model';
import { Aluno } from './../../../model/aluno.model';
import { ValidadorCPF } from './../../../util/validator/cpf-validador';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { SelectItem } from 'primeng/components/common/api';
import { ToastyService } from 'ng2-toasty';

import { ErrorHandleService } from './../../../service/error-handle.service';

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

  constructor(
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
      'dataNascimento': [null, [Validators.required, ValidadorCPF.validate]],
      'sexo': [null, Validators.required],

      'email': this.formBuilder.group({
        'codigo': [null],
        'dscEmail': [null, [Validators.required, Validators.email]],
      }),

    });
    this.iniciaSexo();
  }

  iniciaSexo() {
    this.sexo = [];
    this.sexo.push({ label: 'Selecione...', value: null });
    this.sexo.push({ label: 'Masculino', value: 'MASCULINO' });
    this.sexo.push({ label: 'Feminino', value: 'FEMININO' });
  }

  salvar() {
    this.aluno = this.alunoForm.value;
    this.aluno.matriculas.push(this.matricula);
    console.log(this.aluno);
  }

  alterouTurma(event) {
    this.matricula.turma = event;
    console.log(this.aluno);
  }
}
