import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
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
}
