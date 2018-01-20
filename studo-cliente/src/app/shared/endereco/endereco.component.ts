import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ToastyService } from 'ng2-toasty';

import { Mensagem } from './../../model/mensagens.model';
import { Endereco } from './../../model/endereco.model';
import { EnderecoService } from './../../service/endereco.service';

@Component({
  selector: 'app-endereco',
  templateUrl: './endereco.component.html',
  styleUrls: ['./endereco.component.css']
})
export class EnderecoComponent implements OnInit {

  endereco: Endereco;
  enderecoForm: FormGroup;

  constructor(
    private enderecoService: EnderecoService,
    private formBuilder: FormBuilder,
    private toasty: ToastyService,
  ) { }

  ngOnInit() {
    this.enderecoForm = this.formBuilder.group({
      codigo: this.formBuilder.control(null),
      cep: this.formBuilder.control(null, Validators.required),
      uf: this.formBuilder.control(null, Validators.required),
      localidade: this.formBuilder.control(null, Validators.required),
      logradouro: this.formBuilder.control(null, Validators.required),
      bairro: this.formBuilder.control(null, Validators.required),
      numero: this.formBuilder.control(null, Validators.required),
      complemento: this.formBuilder.control(''),
    });
  }

  buscaCep(event) {
    const cep = event.replace(/[^a-zA-Z0-9]/g, '');
    this.enderecoService.buscarCep(cep).then(resulatdo => {
      this.preencherEndereco(resulatdo);
    });
  }

  preencherEndereco(endereco: any) {
    if (endereco.erro === true) {
      this.toasty.info(Mensagem.CEP_NAO_ENCONTRADO);
    } else {
      this.enderecoForm.controls['uf'].setValue(endereco.uf);
      this.enderecoForm.controls['localidade'].setValue(endereco.localidade);
      this.enderecoForm.controls['logradouro'].setValue(endereco.logradouro);
      this.enderecoForm.controls['bairro'].setValue(endereco.bairro);
      this.enderecoForm.controls['complemento'].setValue(endereco.complemento);


    }
  }

}
