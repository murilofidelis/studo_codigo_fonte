import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { NotAuthenticatedError } from '../seguranca/studo-http';

@Injectable()
export class ErrorHandleService {

  constructor(
    private router: Router,
    private toasty: ToastyService
  ) { }

  handle(errorResponse: any) {

    let msg: string;

    if (errorResponse.status >= 400 && errorResponse.status <= 499) {
      let errors;
      try {
        errors = errorResponse.json();
        msg = errors[0];

        if (errorResponse.status === 403) {
          msg = 'Você não possui permissão para realizar esta ação';
        }
      } catch (e) { }
      console.error('Ocorreu um erro:', errorResponse);
    } else if (errorResponse instanceof NotAuthenticatedError) {

      this.router.navigate(['/login']);

    } else {
      msg = 'Ocorreu um erro inesperado.';
      console.error('Ocorreu um erro:', errorResponse);
    }
    if (msg) {
      this.toasty.error(msg);
    }
  }
}
