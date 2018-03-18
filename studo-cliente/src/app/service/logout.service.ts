import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';

import { AuthService } from './auth.service';
import { STUDO_API } from '../app.api';

@Injectable()
export class LogoutService {

  END_POINT = 'logout';

  constructor(
    private http: AuthHttp,
    private auth: AuthService
  ) { }

  logout() {
    return this.http.delete(`${STUDO_API}/${this.END_POINT}/revoke`, { withCredentials: true })
      .toPromise()
      .then(() => {
        this.auth.limparAccessToken();
      });
  }

}
