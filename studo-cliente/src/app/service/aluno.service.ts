import { ErrorHandleService } from './error-handle.service';
import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { URLSearchParams, Headers, RequestOptions, ResponseContentType } from '@angular/http';

import * as moment from 'moment';
import { Observable } from 'rxjs/Observable';

import { STUDO_API } from './../app.api';

import { Matricula } from './../model/matricula.model';
import { Aluno } from './../model/aluno.model';

export class AlunoFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 10;
}

@Injectable()
export class AlunoService {

  END_POINT = 'alunos';

  constructor(
    private http: AuthHttp,
    private erroHandle: ErrorHandleService
  ) { }

  pesquisar(filtro: AlunoFiltro) {

    const params = new URLSearchParams();

    if (filtro.nome) {
      params.set('nome', filtro.nome);
    }
    params.set('page', filtro.pagina.toString());
    params.set('size', filtro.itensPorPagina.toString());

    return this.http.get(`${STUDO_API}/${this.END_POINT}`, { search: params })
      .toPromise()
      .then(response => {
        const responseJson = response.json();
        const alunos = responseJson.content;

        const resultado = {
          alunos,
          total: responseJson.totalElements
        };
        return resultado;
      });
  }

  buscaPorCodigo(codigo: number) {
    return this.http.get(`${STUDO_API}/${this.END_POINT}/${codigo}`)
      .toPromise()
      .then(response => {
        const aluno = response.json() as Aluno;
        this.converteStringParaData(aluno);
        return aluno;
      });
  }

  salvar(aluno: Aluno) {
    return this.http.post(`${STUDO_API}/${this.END_POINT}`, aluno)
      .toPromise()
      .then(res => res)
      .catch(erro => this.erroHandle.handle(erro));
  }

  salvarMatricula(matricula: Matricula) {
    return this.http.post(`${STUDO_API}/${this.END_POINT}/matricula`, matricula)
      .toPromise()
      .then(res => res)
      .catch(error => this.erroHandle.handle(error));
  }

  private converteStringParaData(aluno: Aluno) {
    aluno.dataNascimento = moment(aluno.dataNascimento, 'YYYY-MM-DD').toDate();
  }

  buscaMatriculasPorAluno(codigoAluno: number) {
    return this.http.get(`${STUDO_API}/${this.END_POINT}/matriculas/${codigoAluno}`)
      .toPromise()
      .then(response => response.json());
  }

  verificaCpfCadastrado(cpf: string): Observable<boolean> {
    return this.http.get(`${STUDO_API}/${this.END_POINT}/verifica/${cpf}`)
      .map(response => response.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  excluirMatricula(codigoMatricula: number) {
    return this.http.delete(`${STUDO_API}/${this.END_POINT}/${codigoMatricula}`)
      .toPromise()
      .then(res => res.json())
      .catch(erro => this.erroHandle.handle(erro));
  }

  downloadRelatorio() {
    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    const opt = new RequestOptions({ headers: headers, responseType: ResponseContentType.Blob });
    return this.http.get(`${STUDO_API}/${this.END_POINT}/geraRelatorioAlunos`, opt)
      .map(res => {
        return new Blob([res.blob()], { type: 'application/x-www-form-urlencoded' });
      }).catch((error: any) => Observable.throw(error.json() || 'Erro ao realizar download'));
  }

}
