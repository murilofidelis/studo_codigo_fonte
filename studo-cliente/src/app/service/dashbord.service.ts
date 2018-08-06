import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { STUDO_API } from '../app.api';

@Injectable()
export class DashbordService {

  END_POINT = 'dashbord';

  constructor(private http: AuthHttp) { }

  buscarDadosDashbord() {
    return this.http.get(`${STUDO_API}/${this.END_POINT}`)
      .map(response => response.json())
      .catch(erro => Observable.throw(erro));
  }

  downloadRelatorio() {
    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    const opt = new RequestOptions({ headers: headers, responseType: ResponseContentType.Blob });
    return this.http.get(`${STUDO_API}/${this.END_POINT}/geraRelatorio`, opt)
      .map(res => {
        return new Blob([res.blob()], { type: 'application/x-www-form-urlencoded' });
      }).catch((error: any) => Observable.throw(error.json() || 'Erro ao realizar download'));
  }
}
