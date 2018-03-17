import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '../../service/auth.service';
import { ErrorHandleService } from '../../service/error-handle.service';
import { ToastyService } from 'ng2-toasty';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(
    private auth: AuthService,
    private errorHandle: ErrorHandleService,
    private toasty: ToastyService,
    private route: Router
  ) { }

  login(cpf: string, senha: string) {
    this.auth.login(cpf, senha)
      .then(() => {
        this.route.navigate(['/aluno']);
      })
      .catch(erro => {
        if (erro.error === 'invalid_grant') {
          this.toasty.error('Usuário ou senha inválidos!');
        } else {
          this.errorHandle.handle(erro);
        }
      });
  }

}
