import { Injectable } from '@angular/core';
import { ToastyService } from 'ng2-toasty';

@Injectable()
export class ErrorHandleService {

  constructor(private toasty: ToastyService) { }

  handle(errorResponse: any) {

    let msg: string;

    if (errorResponse.status >= 400 && errorResponse.status <= 499) {
      let errors;
      try {
        errors = errorResponse.json();
        msg = errors[0].mensagemErro;
      } catch (e) { }
      console.error('Ocorreu um erro:', errorResponse);
    } else {
      msg = 'Ocorreu um erro inesperado.';
      console.error('Ocorreu um erro:', errorResponse);
    }
    this.toasty.error(msg);
  }
}
