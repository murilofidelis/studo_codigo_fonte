import { Http, Headers, URLSearchParams } from '@angular/http';
import { Injectable } from '@angular/core';

import 'rxjs/add/operator/toPromise';

import { STUDO_API } from '../app.api';

@Injectable()
export class EnderecoService {

  VIA_CEP_END_POINT = 'https://viacep.com.br/ws';

  constructor(private http: Http) { }

  buscarCep(cep: string): Promise<any> {
    return this.http.get(`${this.VIA_CEP_END_POINT}/${cep}/json`)
      .toPromise()
      .then(response => {
        return response.json();
      });
  }


}
