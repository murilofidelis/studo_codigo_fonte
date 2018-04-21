import { Atividade } from './../model/atividade.model';
import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';

import { STUDO_API } from '../app.api';

import { ErrorHandleService } from './error-handle.service';

@Injectable()
export class AtividadeService {

  END_POINT = 'atividade';

  constructor(
    private http: AuthHttp,
    private errorHandle: ErrorHandleService
  ) { }

  buscaClassificacoes() {
    return this.http.get(`${STUDO_API}/${this.END_POINT}/listaClassificacao`)
      .toPromise()
      .then(res => res.json())
      .catch(erro => this.errorHandle.handle(erro));
  }

  salvar(atividade: Atividade) {
    return this.http.post(`${STUDO_API}/${this.END_POINT}`, JSON.stringify(atividade))
      .toPromise()
      .then(res => res)
      .catch(erro => this.errorHandle.handle(erro));
  }
}
