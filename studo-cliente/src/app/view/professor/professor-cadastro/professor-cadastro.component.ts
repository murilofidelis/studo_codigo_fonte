import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ToastyService } from 'ng2-toasty';

import { SelectItem } from 'primeng/components/common/api';

import { Professor } from './../../../model/professor.model';

@Component({
  selector: 'app-professor-cadastro',
  templateUrl: './professor-cadastro.component.html',
  styleUrls: ['./professor-cadastro.component.css']
})
export class ProfessorCadastroComponent implements OnInit {

  sexo: SelectItem[];
  status = [];
  professorForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private toasty: ToastyService
  ) { }

  ngOnInit() {
    this.professorForm = this.formBuilder.group({
      codigo: this.formBuilder.control(null),
      nome: this.formBuilder.control(null, Validators.required),
      cpf: this.formBuilder.control(null, Validators.required),
      sexo: this.formBuilder.control(null, Validators.required),
      email: this.formBuilder.control(null, Validators.required),
    });
    this.iniciarSexo();
    this.iniciaStatus();
  }

  iniciarSexo() {
    this.sexo = [];
    this.sexo.push({ label: 'Selecione...', value: null });
    this.sexo.push({ label: 'Masculino', value: 'MASCULINO' });
    this.sexo.push({ label: 'Feminino', value: 'FEMININO' });
  }

  iniciaStatus() {
    this.status.push({ label: 'Ativo', value: true });
    this.status.push({ label: 'Inativo', value: false });
  }

  salvar(professor: Professor) {
    console.log(professor);
  }
}
