import { Matricula } from './../model/matricula.model';
import { Aluno } from './../model/aluno.model';
import { Injectable } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';

import * as moment from 'moment';
import { Observable } from 'rxjs/Observable';

import { STUDO_API } from './../app.api';

export class AlunoFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 10;
}

@Injectable()
export class AlunoService {

  END_POINT = 'alunos';

  constructor(private http: Http) { }

  pesquisar(filtro: AlunoFiltro) {
    const headers = new Headers();
    const params = new URLSearchParams();

    if (filtro.nome) {
      params.set('nome', filtro.nome);
    }
    params.set('page', filtro.pagina.toString());
    params.set('size', filtro.itensPorPagina.toString());

    return this.http.get(`${STUDO_API}/${this.END_POINT}`, { headers, search: params })
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
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post(`${STUDO_API}/${this.END_POINT}`, JSON.stringify(aluno), { headers })
      .toPromise()
      .then(() => null);
  }

  salvarMatricula(matricula: Matricula) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post(`${STUDO_API}/${this.END_POINT}/matricula`, JSON.stringify(matricula), { headers })
      .toPromise()
      .then(() => null);
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
      .then(() => null);
  }

}
