import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ToastyService } from 'ng2-toasty';

import { Mensagem } from '../../util/mensagens.util';

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
      complemento: this.formBuilder.control(null),
    });
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
      this.enderecoForm.get('uf').setValue(endereco.uf);
      this.enderecoForm.get('localidade').setValue(endereco.localidade);
      this.enderecoForm.get('logradouro').setValue(endereco.logradouro);
      this.enderecoForm.get('bairro').setValue(endereco.bairro);
      this.enderecoForm.get('complemento').setValue(endereco.complemento);

      this.endereco = this.enderecoForm.value;
      console.log(this.endereco);
    }
  }

}
