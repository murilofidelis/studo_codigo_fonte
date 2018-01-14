import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ToastyModule } from 'ng2-toasty';

import { DisciplinaService } from '../service/disciplina.service';
import { TurmaService } from './../service/turma.service';
import { ErrorHandleService } from '../service/error-handle.service';
import { NavbarComponent } from './navbar/navbar.component';


@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    ToastyModule.forRoot()
  ],
  exports: [
    ToastyModule,
    NavbarComponent
  ],
  providers: [
    DisciplinaService,
    TurmaService,
    ErrorHandleService],
  declarations: [NavbarComponent]
})
export class CoreModule { }
