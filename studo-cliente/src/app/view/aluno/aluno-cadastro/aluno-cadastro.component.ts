import { CalendarioUtil } from './../../../util/calendario.util';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { SelectItem } from 'primeng/components/common/api';
import { ToastyService } from 'ng2-toasty';

import { Mensagem } from '../../../util/mensagens.util';

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

  cpfCadastrado: boolean;
  disableCpf: boolean;

  constructor(
    private alunoService: AlunoService,
    private formBuilder: FormBuilder,
    private toasty: ToastyService,
    private activatedRoute: ActivatedRoute,
    private route: Router,
    private errorHandle: ErrorHandleService
  ) { }

  ngOnInit() {
    const codigoAluno = this.activatedRoute.snapshot.params['codigo'];

    this.alunoForm = this.formBuilder.group({
      'codigo': [null],
      'nome': [null, [Validators.required, Validators.maxLength(50)]],
      'cpf': [null, [Validators.required, Validators.minLength(11), ValidadorCPF.validate]],
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

    if (codigoAluno) {
      this.carregaAluno(codigoAluno);
    }
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
      setTimeout(() => {
        this.route.navigate(['/aluno']);
      }, 1000);
    }).catch(erro => this.errorHandle.handle(erro));

  }

  carregaAluno(codigo: number) {
    this.alunoService.buscaPorCodigo(codigo)
      .then(aluno => {
        this.aluno = aluno;
        this.alunoForm.setValue(this.aluno);
      }).catch(erro => this.errorHandle.handle(erro));
  }


  traduzirCalendar() {
    this.pt = CalendarioUtil.pt;
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

  verificaCpfValido(): boolean {
    const cpf = this.alunoForm.get('cpf');
    return (cpf.invalid && cpf.dirty);
  }


  verificaCpfCadastrado(event) {
    const cpf = this.removeMascara(event.target.value);
    if (cpf) {
      this.alunoService.verificaCpfCadastrado(cpf).subscribe(result => {
        this.cpfCadastrado = result;
      });
    }
  }

  removeMascara(valor: string): string {
    const valorSemFormatacao = valor.replace(/[^a-zA-Z0-9]/g, '');
    return valorSemFormatacao;
  }

}
