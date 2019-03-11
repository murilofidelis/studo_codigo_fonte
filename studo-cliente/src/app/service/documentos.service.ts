import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { Headers, URLSearchParams, RequestOptions, ResponseContentType } from '@angular/http';

import { STUDO_API } from '../app.api';
import { Documento } from '../model/documento.model';

@Injectable()
export class DocumentosService {

  END_POINT = 'documentos';

  constructor(
    private http: AuthHttp
  ) { }

  listar(codigo: number) {
    return this.http.get(`${STUDO_API}/${this.END_POINT}/listaPorAluno/${codigo}`)
      .map(res => res.json());
  }

  upload(documentos: Documento[]) {
    return this.http.post(`${STUDO_API}/${this.END_POINT}/upload`, documentos);
  }

  download(caminho: string) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');

    const params = new URLSearchParams();
    params.set('caminho', caminho);

    const opt = new RequestOptions({ headers: headers, search: params, responseType: ResponseContentType.Blob });

    return this.http.get(`${STUDO_API}/${this.END_POINT}/download`, opt)
      .map(res => {
        return new Blob([res.blob()]);
      });
  }

  delete(id: number) {
    return this.http.delete(`${STUDO_API}/${this.END_POINT}/${id}`);
  }

}
