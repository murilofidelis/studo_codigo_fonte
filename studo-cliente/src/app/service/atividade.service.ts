import { URLSearchParams } from '@angular/http';
import { Atividade } from './../model/atividade.model';
import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';

import { STUDO_API } from '../app.api';

import { ErrorHandleService } from './error-handle.service';

export class FiltroAtividade {
  dataInicio: Date;
  dataFim: Date;
  pagina = 0;
  itensPorPagina = 10;
}

@Injectable()
export class AtividadeService {

  END_POINT = 'atividade';

  constructor(
    private http: AuthHttp,
    private errorHandle: ErrorHandleService
  ) { }

  pesquisar(filtro: FiltroAtividade) {
    const params = new URLSearchParams();

    if (filtro.dataInicio) {
      params.set('dataInicio', this.formataDataParaPesquisa(filtro.dataInicio).toString());
    }
    if (filtro.dataFim) {
      params.set('dataFim', this.formataDataParaPesquisa(filtro.dataFim).toString());
    }
    params.set('page', filtro.pagina.toString());
    params.set('size', filtro.itensPorPagina.toString());

    return this.http.get(`${STUDO_API}/${this.END_POINT}`, { search: params })
      .toPromise()
      .then(response => {
        const responseJson = response.json();
        const atividades = responseJson.content;

        const resultado = {
          atividades,
          total: responseJson.totalElements
        };
        return resultado;
      });
  }

  formataDataParaPesquisa(data: Date): String {
    return `${data.getFullYear()}/${data.getMonth() + 1}/${data.getDate()}`;
  }

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
