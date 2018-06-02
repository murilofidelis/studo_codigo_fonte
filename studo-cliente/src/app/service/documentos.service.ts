import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { Headers, RequestOptions, ResponseContentType } from '@angular/http';

import { Observable } from 'rxjs/Observable';

import { STUDO_API } from '../app.api';
import { ErrorHandleService } from './error-handle.service';

@Injectable()
export class DocumentosService {
  END_POINT = 'documentos';

  constructor(
    private http: AuthHttp,
    private erroHandle: ErrorHandleService
  ) { }

  listar(codigo: number) {
    return this.http.get(`${STUDO_API}/${this.END_POINT}/listaPorAluno/${codigo}`)
      .toPromise()
      .then(response => response.json());
  }

  download(id: number) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    const opt = new RequestOptions({ headers: headers, responseType: ResponseContentType.Blob });
    return this.http.get(`${STUDO_API}/${this.END_POINT}/download/${id}`, opt)
      .map(res => {
        return new Blob([res.blob()], { type: 'application/x-www-form-urlencoded' });
      }).catch((error: any) => Observable.throw(error.json() || 'Erro ao realizar download'));
  }

}
