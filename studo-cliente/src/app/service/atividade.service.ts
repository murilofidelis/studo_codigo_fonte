import { URLSearchParams } from '@angular/http';
import { Atividade } from './../model/atividade.model';
import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';

import { STUDO_API } from '../app.api';

import * as moment from 'moment';

import { ErrorHandleService } from './error-handle.service';
import { Pageable } from '../util/pageable';

export class FiltroAtividade {
  dataInicio: Date;
  dataFim: Date;
  titulo: string;
  codDisciplina: number;
}

@Injectable()
export class AtividadeService {

  END_POINT = 'atividade';

  constructor(
    private http: AuthHttp,
    private errorHandle: ErrorHandleService
  ) { }

  filtrar(filtro: FiltroAtividade, pageable: Pageable) {

    const params = new URLSearchParams();

    if (filtro.dataInicio) {
      params.set('dataInicio', moment(filtro.dataInicio).format('YYYY-MM-DD'));
    }
    if (filtro.dataFim) {
      params.set('dataFim', moment(filtro.dataFim).format('YYYY-MM-DD'));
    }
    if (filtro.codDisciplina) {
      params.set('codigoDisciplina', filtro.codDisciplina.toString());
    }
    if (filtro.titulo) {
      params.set('titulo', filtro.titulo.toString());
    }

    params.set('page', pageable.page.toString());
    params.set('size', pageable.size.toString());
    params.set('sortField', pageable.sortField.toString());
    params.set('sortOrder', pageable.sortOrder.toString());

    return this.http.get(`${STUDO_API}/${this.END_POINT}`, { search: params })
      .map(response => {
        const responseJson = response.json();
        const atividades = responseJson.content;

        const resultado = {
          atividades,
          total: responseJson.totalElements
        };
        return resultado;
      });
  }

  /**Pesquisa antiga usando query nativa */
  pesquisar(filtro: FiltroAtividade, pageable: Pageable) {

    const params = new URLSearchParams();

    if (filtro.dataInicio) {
      params.set('dataInicio', this.formataDataParaPesquisa(filtro.dataInicio).toString());
    }
    if (filtro.dataFim) {
      params.set('dataFim', this.formataDataParaPesquisa(filtro.dataInicio).toString());
    }
    if (filtro.codDisciplina) {
      params.set('codigoDisciplina', filtro.codDisciplina.toString());
    }
    if (filtro.titulo) {
      params.set('titulo', filtro.titulo.toString());
    }

    params.set('page', pageable.page.toString());
    params.set('size', pageable.size.toString());

    return this.http.get(`${STUDO_API}/${this.END_POINT}/pesquisa`, { search: params })
      .map(response => {
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

  buscaPorCodigo(codigo: number) {
    return this.http.get(`${STUDO_API}/${this.END_POINT}/${codigo}`)
      .toPromise()
      .then(response => {
        const atividade = response.json() as Atividade;
        return atividade;
      });
  }

  exluirAtividade(codigo: number) {
    return this.http.delete(`${STUDO_API}/${this.END_POINT}/${codigo}`)
      .toPromise()
      .then(res => res)
      .catch(erro => this.errorHandle.handle(erro));
  }
}
