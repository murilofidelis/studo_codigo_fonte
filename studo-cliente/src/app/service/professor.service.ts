import { STUDO_API } from './../app.api';
import { Injectable } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Professor } from './../model/professor.model';

@Injectable()
export class ProfessorService {

  END_POINT = 'professor';

  constructor(private http: Http) { }

  salvar(professor: Professor) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post(`${STUDO_API}/${this.END_POINT}`, JSON.stringify(professor), { headers })
      .toPromise()
      .then(() => null);
  }

}
