import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { STUDO_API } from '../app.api';
import { Disciplina } from '../model/disciplina.model';

export class DisciplinaFiltro {
  descricao: string;
  pagina = 0;
  itensPorPagina = 10;
}

@Injectable()
export class DisciplinaService {

  END_POINT = 'disciplinas';

  constructor(private http: AuthHttp) { }

  pesquisar(filtro: DisciplinaFiltro): Promise<any> {
    const params = new URLSearchParams();
    const headers = new Headers();

    params.set('page', filtro.pagina.toString());
    params.set('size', filtro.itensPorPagina.toString());

    if (filtro.descricao) {
      params.set('descricao', filtro.descricao);
    }

    return this.http.get(`${STUDO_API}/${this.END_POINT}`, { search: params })
      .toPromise()
      .then(response => {
        const responseJson = response.json();
        const disciplinas = responseJson.content;

        const resultado = {
          disciplinas,
          total: responseJson.totalElements
        };
        return resultado;
      });
  }

  salvar(disciplina: Disciplina): Promise<any> {
    const headers = new Headers();
    return this.http.post(`${STUDO_API}/${this.END_POINT}`, JSON.stringify(disciplina))
      .toPromise()
      .then(() => null);
  }

  alterar(disciplina: Disciplina) {
    const headers = new Headers();
    return this.http.put(`${STUDO_API}/${this.END_POINT}`, JSON.stringify(disciplina))
      .toPromise()
      .then((response => {
        const disciplinaAlterada = response.json() as Disciplina;
        return disciplinaAlterada;
      }));
  }

  buscaTodas() {
    return this.http.get(`${STUDO_API}/${this.END_POINT}/listaTodas`)
      .toPromise()
      .then(disciplinas => disciplinas.json());
  }

}
