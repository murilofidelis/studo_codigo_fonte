import { Component, OnInit, Input, OnChanges, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ToastyService } from 'ng2-toasty';

import { Mensagem } from '../../util/mensagens.util';

import { Endereco } from './../../model/endereco.model';
import { EnderecoService } from './../../service/endereco.service';
import { Professor } from '../../model/professor.model';

@Component({
  selector: 'app-endereco',
  templateUrl: './endereco.component.html',
  styleUrls: ['./endereco.component.css']
})
export class EnderecoComponent implements OnInit, OnChanges {

  @Input() professor: Professor;

  @Output() enderecoEmit = new EventEmitter<Endereco>();

  enderecoForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private toasty: ToastyService,
    private enderecoService: EnderecoService,
  ) { }


  ngOnChanges(): void {
    if (this.professor && this.professor.codigo) {
      this.preencheFormulario(this.professor.endereco);
    }
  }

  ngOnInit() {
    this.enderecoForm = this.fb.group({
      cep: [null, [Validators.required]],
      uf: [null, [Validators.required]],
      localidade: [null, [Validators.required]],
      logradouro: [null, [Validators.required]],
      bairro: [null, [Validators.required]],
      numero: [null, [Validators.required]],
      complemento: [null],
    });
    this.verificaMudanca();
  }

  preencheFormulario(endereco) {
    this.enderecoForm.patchValue(endereco);
  }

  verificaMudanca() {
    this.enderecoForm.statusChanges.subscribe(() => {
      if (this.enderecoForm.valid) {
        this.enderecoEmit.emit(this.enderecoForm.value);
      } else {
        this.enderecoEmit.emit(null);
      }
    });
  }

  buscaCep(event) {
    const cep = event.replace(/[^a-zA-Z0-9]/g, '');
    this.enderecoService.buscarCep(cep).then(endereco => {
      if (endereco.erro === true) {
        this.toasty.info(Mensagem.CEP_NAO_ENCONTRADO);
      } else {
        this.preencheFormulario(endereco)
      }
    });
  }

}
