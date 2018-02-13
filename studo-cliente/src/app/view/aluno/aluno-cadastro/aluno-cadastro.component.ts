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
  status = [];
  alunoForm: FormGroup;
  aluno: Aluno = new Aluno();
  pt: any;

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
      'nome': [null, [Validators.required, Validators.maxLength(50)]],
      'dataNascimento': [null, Validators.required],
      'sexo': [null, Validators.required],
      'status': [true],

      'email': this.formBuilder.group({
        'codigo': [null],
        'dscEmail': [null, [Validators.required, Validators.email]],
      }),

    });
    this.iniciaSexo();
    this.iniciaStatus();
    this.traduzirCalendar();
  }

  iniciaSexo() {
    this.sexo = [];
    this.sexo.push({ label: 'Selecione...', value: null });
    this.sexo.push({ label: 'Masculino', value: 'MASCULINO' });
    this.sexo.push({ label: 'Feminino', value: 'FEMININO' });
  }

  iniciaStatus() {
    this.status.push({ label: 'Ativo', value: true });
    this.status.push({ label: 'Inativo', value: false });
  }

  salvar() {
    this.aluno = this.alunoForm.value;
    this.alunoService.salvar(this.aluno).then(() => {
      this.alunoForm.reset();
      this.toasty.success(Mensagem.MENSAGEM_SALVO_SUCESSO);
    }).catch(erro => this.errorHandle.handle(erro));

  }

  traduzirCalendar() {
    this.pt = {
      dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
      dayNamesShort: ['dom', 'sen', 'ter', 'quar', 'quin', 'sex', 'sáb'],
      dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
      monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro',
        'Outubro', 'Novembro', 'Dezembro'],
      monthNamesShort: ['jan ', 'fev', 'mar', 'abr', 'mai', 'jun', 'jul', 'ago', 'set', 'out', 'nov', 'dez'],
      dateFormat: 'dd/mm/yy'
    };
  }

  verificaCampoContenErro(campo: string): boolean {
    return (!this.alunoForm.get(campo).value) &&
      (this.alunoForm.get(campo).touched || this.alunoForm.get(campo).dirty);
  }

  verificaEmailValido() {
    const email = this.alunoForm.get('email.dscEmail');
    if (email.errors) {
      return email.errors['email'] && email.touched;
    }
  }

}
