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

    return this.http.post(`${STUDO_API}/${this.END_POINT}`, body, { headers, withCredentials: true })
      .toPromise()
      .then(response => {
        this.armazenarToken(response.json().access_token);
      }).catch(response => {
        return Promise.reject(response.json());
      });
  }

  novoAccessToken(): Promise<void> {

    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    headers.append('Authorization', 'Basic c3R1ZG9fY2xpZW50ZTpzdHVkMF9jbGkzbnQzX3MzY3JldA==');

    const body = 'grant_type=refresh_token';

    return this.http.post(`${STUDO_API}/${this.END_POINT}`, body, { headers, withCredentials: true })
      .toPromise()
      .then(response => {
        this.armazenarToken(response.json().access_token);
        return Promise.resolve(null);
      }).catch(erro => {
        return Promise.resolve(null);
      });
  }

  isAccessTokenInvalido() {
    const token = localStorage.getItem('token');
    return !token || this.jwtHelper.isTokenExpired(token);
  }

  possuiPermissao(permissao: string): boolean {
    if (permissao) {
      return this.jwtPayload && this.jwtPayload.authorities.includes(permissao);
    }
  }

  possuiQualquerPermisso(roles): boolean {
    for (const role of roles) {
      if (this.possuiPermissao(role)) {
        return true;
      }
    }
    return false;
  }

  limparAccessToken() {
    localStorage.removeItem('token');
    this.jwtPayload = null;
  }

  private armazenarToken(token: string) {
    this.jwtPayload = this.jwtHelper.decodeToken(token);
    localStorage.setItem('token', token);
  }

  carregarToken(): string {
    const token = localStorage.getItem('token');
    if (token) {
      this.armazenarToken(token);
    }
    return token;
  }

}
