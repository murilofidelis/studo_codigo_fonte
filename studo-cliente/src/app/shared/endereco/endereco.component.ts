import { Component, OnInit } from '@angular/core';

import { Endereco } from './../../model/endereco.model';
import { EnderecoService } from './../../service/endereco.service';

@Component({
  selector: 'app-endereco',
  templateUrl: './endereco.component.html',
  styleUrls: ['./endereco.component.css']
})
export class EnderecoComponent implements OnInit {

  endereco: Endereco;

  constructor(
    private enderecoService: EnderecoService
  ) { }

  ngOnInit() {
  }

  buscaCep(event: any) {
    console.log(event);
    let cep: String = '';
    cep = event.target.value.replace(/[^a-zA-Z0-9]/g, '');

    console.log(cep);
    this.enderecoService.buscarCep(event.target.value).then(resulatdo => {

      this.endereco = resulatdo;
      console.log(this.endereco);
    });
  }

}
