import { Component } from '@angular/core';

import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private auth: AuthService) { }

  login(cpf: string, senha: string) {
    this.auth.login(cpf, senha);
  }

}
