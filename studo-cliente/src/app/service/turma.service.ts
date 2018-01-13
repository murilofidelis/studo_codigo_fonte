
import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Turma } from './../model/turma.model';
import { STUDO_API } from '../app.api';

@Injectable()
export class TurmaService {

  END_POINT = 'turmas';

  constructor(private http: Http) { }

  salvar(turma: Turma): Promise<any> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post(`${STUDO_API}/${this.END_POINT}`, JSON.stringify(turma), { headers })
      .toPromise()
      .then(() => null);
  }

}
