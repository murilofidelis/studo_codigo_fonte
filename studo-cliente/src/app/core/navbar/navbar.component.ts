import { Router } from '@angular/router';
import { ErrorHandleService } from './../../service/error-handle.service';
import { Component } from '@angular/core';

import { AuthService } from '../../service/auth.service';
import { LogoutService } from '../../service/logout.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(
    private router: Router,
    private auth: AuthService,
    private logoutService: LogoutService,
    private errorHandle: ErrorHandleService,
  ) { }

  logout() {
    this.logoutService.logout()
      .then(() => {
        this.router.navigate(['/login']);
      }).catch(erro => this.errorHandle.handle(erro));
  }

}
