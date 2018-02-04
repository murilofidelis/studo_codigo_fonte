import { Injectable } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';

import { STUDO_API } from './../app.api';
import { Professor } from './../model/professor.model';

export class ProfessorFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 10;
}

@Injectable()
export class ProfessorService {

  END_POINT = 'professor';

  constructor(private http: Http) { }

  pesuisar(filtro: ProfessorFiltro) {
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
        const professores = responseJson.content;

        const resultado = {
          professores,
          total: responseJson.totalElements
        };
        return resultado;
      });
  }

  verificaCpfCadastrado(cpf: string): Observable<boolean> {
    return this.http.get(`${STUDO_API}/${this.END_POINT}/verifica/${cpf}`)
      .map(response => response.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  buscaPorCodigo(codigo: number) {
    return this.http.get(`${STUDO_API}/${this.END_POINT}/${codigo}`)
      .toPromise()
      .then(response => {
        const professor = response.json() as Professor;
        return professor;
      });
  }

  salvar(professor: Professor): Promise<any> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post(`${STUDO_API}/${this.END_POINT}`, JSON.stringify(professor), { headers })
      .toPromise()
      .then(() => null);
  }

}
