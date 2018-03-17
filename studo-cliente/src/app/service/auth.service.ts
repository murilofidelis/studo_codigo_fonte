import { JwtHelper } from 'angular2-jwt';
import { Http, Headers } from '@angular/http';
import { Injectable } from '@angular/core';

import 'rxjs/add/operator/toPromise';

import { STUDO_API } from './../app.api';

@Injectable()
export class AuthService {

  END_POINT = 'oauth/token';
  jwtPayload: any;

  constructor(
    private http: Http,
    private jwtHelper: JwtHelper
  ) { this.carregarToken(); }

  login(cpf: string, senha: string) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    headers.append('Authorization', 'Basic c3R1ZG9fY2xpZW50ZTpzdHVkMF9jbGkzbnQzX3MzY3JldA==');

    const body = `username=${cpf}&password=${senha}&grant_type=password`;

    return this.http.post(`${STUDO_API}/${this.END_POINT}`, body, { headers })
      .toPromise()
      .then(response => {
        this.armazenarToken(response.json().access_token);
      }).catch(response => {
        return Promise.reject(response.json());
      });
  }

  private armazenarToken(token: string) {
    this.jwtPayload = this.jwtHelper.decodeToken(token);
    localStorage.setItem('token', token);
  }

  private carregarToken() {
    const token = localStorage.getItem('token');
    if (token) {
      this.armazenarToken(token);
    }
  }

}
