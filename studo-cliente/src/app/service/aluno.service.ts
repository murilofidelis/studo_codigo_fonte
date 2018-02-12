import { Aluno } from './../model/aluno.model';
import { Injectable } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';

import { STUDO_API } from './../app.api';

@Injectable()
export class AlunoService {

  END_POINT = 'alunos';

  constructor(private http: Http) { }

  salvar(aluno: Aluno) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post(`${STUDO_API}/${this.END_POINT}`, JSON.stringify(aluno), { headers })
      .toPromise()
      .then(() => null);
  }

}
