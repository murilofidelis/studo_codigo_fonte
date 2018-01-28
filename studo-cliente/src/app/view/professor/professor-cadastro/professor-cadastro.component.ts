import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ToastyService } from 'ng2-toasty';

import { SelectItem } from 'primeng/components/common/api';

import { Professor } from './../../../model/professor.model';
import { EnderecoService } from '../../../service/endereco.service';
import { ProfessorService } from './../../../service/professor.service';
import { Mensagem } from '../../../model/mensagens.model';
import { ValidadorCPF } from './../../../util/validator/cpf-validador';
import { ErrorHandleService } from '../../../service/error-handle.service';

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

  constructor(
    private formBuilder: FormBuilder,
    private toasty: ToastyService,
    private route: Router,
    private professorService: ProfessorService,
    private enderecoService: EnderecoService,
    private errorHandle: ErrorHandleService) { }

  ngOnInit() {
    this.professorForm = this.formBuilder.group({
      'codigo': [null],
      'nome': [null, Validators.required],
      'cpf': [null, [Validators.required, Validators.minLength(11)]],
      'sexo': [null, Validators.required],

      'email': this.formBuilder.group({
        'codigo': [null],
        'dscEmail': [null, [Validators.required, Validators.email]],
      }),

      endereco: this.formBuilder.group({
        'codigo': [null],
        'cep': [null, Validators.required],
        'uf': [null, Validators.required],
        'localidade': [null, Validators.required],
        'logradouro': [null, Validators.required],
        'bairro': [null, Validators.required],
        'numero': [null, Validators.required],
        'complemento': [null],
      })
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

  buscaCep(event) {
    const cep = event.replace(/[^a-zA-Z0-9]/g, '');
    this.enderecoService.buscarCep(cep).then(resulatdo => {
      this.preencheEndereco(resulatdo);
    });
  }

  preencheEndereco(endereco: any) {
    if (endereco.erro === true) {
      this.toasty.info(Mensagem.CEP_NAO_ENCONTRADO);
    } else {
      this.professorForm.patchValue({
        endereco: {
          uf: endereco.uf,
          localidade: endereco.localidade,
          logradouro: endereco.logradouro,
          bairro: endereco.bairro,
          complemento: endereco.complemento,
        }
      });
    }
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

  verificaCpfCadastrado(event){
    
  }

  salvar() {
    this.professorService.salvar(this.professorForm.value)
      .then(() => {
        this.professor = new Professor();
        this.toasty.success(Mensagem.MENSAGEM_SALVO_SUCESSO);
        this.professorForm.reset();
        setTimeout(() => {
          // this.route.navigate(['/turmas']);
        });
      }).catch(erro => this.errorHandle.handle(erro));
  }

}
