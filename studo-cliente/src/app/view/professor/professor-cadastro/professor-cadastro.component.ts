import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ToastyService } from 'ng2-toasty';

import { SelectItem } from 'primeng/components/common/api';

import { Mensagem } from '../../../util/mensagens.util';
import { ValidadorCPF } from './../../../util/validator/cpf-validador';

import { Professor } from './../../../model/professor.model';
import { EnderecoService } from '../../../service/endereco.service';
import { ProfessorService } from './../../../service/professor.service';
import { ErrorHandleService } from '../../../service/error-handle.service';
import { Endereco } from '../../../model/endereco.model';

@Component({
  selector: 'app-professor-cadastro',
  templateUrl: './professor-cadastro.component.html',
  styleUrls: ['./professor-cadastro.component.css']
})
export class ProfessorCadastroComponent implements OnInit {

  sexo: SelectItem[];

  status = [];

  professorForm: FormGroup;

  professor = new Professor();

  cpfCadastrado: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private toasty: ToastyService,
    private activatedRoute: ActivatedRoute,
    private route: Router,
    private professorService: ProfessorService,
    private errorHandle: ErrorHandleService) { }

  ngOnInit() {
    const codigoProfessor = this.activatedRoute.snapshot.params['codigo'];

    this.professorForm = this.formBuilder.group({
      'nome': [null, Validators.required],
      'cpf': [null, [Validators.required, Validators.minLength(11), ValidadorCPF.validate]],
      'sexo': [null, Validators.required],
      'status': [true],

      'email': this.formBuilder.group({
        'codigo': [null],
        'dscEmail': [null, [Validators.required, Validators.email]],
      }),
      endereco: [null, [Validators.required]]
    });
    this.iniciaSexo();
    this.iniciaStatus();
    this.carregaProfessor(codigoProfessor);
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

  verificaCampoContenErro(campo: string): boolean {
    return (!this.professorForm.get(campo).value) &&
      (this.professorForm.get(campo).touched || this.professorForm.get(campo).dirty);
  }

  verificaCpfValido(): boolean {
    const cpf = this.professorForm.get('cpf');
    return (cpf.invalid && cpf.dirty);
  }

  verificaEmailValido() {
    const email = this.professorForm.get('email.dscEmail');
    if (email.errors) {
      return email.errors['email'] && email.touched;
    }
  }

  verificaCpfCadastrado(event) {
    const cpf = this.removeMascara(event.target.value);
    if (cpf) {
      this.professorService.verificaCpfCadastrado(cpf)
        .subscribe(result => {
          this.cpfCadastrado = result;
        });
    }
  }

  carregaProfessor(codigo: number) {
    if (codigo) {
      this.professorService.buscaPorCodigo(codigo)
        .then(professor => {
          this.professor = professor;
          this.professorForm.patchValue(this.professor);
          this.professorForm.get('cpf').disable();
        }).catch(erro => this.errorHandle.handle(erro));
    }
  }

  atualizaEndereco(event: Endereco) {
    this.professorForm.get('endereco').setValue(event);
  }

  bloquearCadastro(): boolean {
    return (this.professorForm.invalid || (this.cpfCadastrado && !this.professor.codigo));
  }

  salvar() {

    const professor = Object.assign(this.professor, this.professorForm.value);

    console.log('SALVANDO ...', professor);

    this.professorService.salvar(professor)
      .then(() => {
        this.professor = new Professor();
        this.toasty.success(Mensagem.MENSAGEM_SALVO_SUCESSO);
        this.professorForm.reset();
        setTimeout(() => {
          this.route.navigate(['/professor']);
        }, 1000);
      }).catch(erro => this.errorHandle.handle(erro));
  }

  removeMascara(valor: string): string {
    const valorSemFormatacao = valor.replace(/[^a-zA-Z0-9]/g, '');
    return valorSemFormatacao;
  }

}
