import { Http, Headers, URLSearchParams } from '@angular/http';
import { Injectable } from '@angular/core';

import 'rxjs/add/operator/toPromise';

import { STUDO_API } from '../app.api';

@Injectable()
export class EnderecoService {

  VIA_CEP_END_POINT = 'https://viacep.com.br/ws';

  constructor(private http: Http) { }

  buscarCep(cep: string): Promise<any> {
    console.log('camando');
    const headers = new Headers();
    return this.http.get(`${this.VIA_CEP_END_POINT}/${cep}/json`, { headers })
      .toPromise()
      .then(response => {
        const endereco = response.json();
        return endereco;
      });
  }


}
