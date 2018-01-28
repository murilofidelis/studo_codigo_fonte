import { STUDO_API } from './../app.api';
import { Injectable } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Professor } from './../model/professor.model';

@Injectable()
export class ProfessorService {

  END_POINT = 'professor';

  constructor(private http: Http) { }

  verificaCpfCadastrado(cpf: string): Promise<any> {
    return this.http.get(`${STUDO_API}/${this.END_POINT}/${cpf}`)
      .toPromise();
  }

  salvar(professor: Professor): Promise<any> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post(`${STUDO_API}/${this.END_POINT}`, JSON.stringify(professor), { headers })
      .toPromise()
      .then(() => null);
  }

}
