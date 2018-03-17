import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ToastyModule } from 'ng2-toasty';
import { JwtHelper } from 'angular2-jwt';

import { PaginaNaoEncontradaComponent } from './pagina-nao-encontrada/pagina-nao-encontrada.component';

import { AuthService } from '../service/auth.service';
import { DisciplinaService } from '../service/disciplina.service';
import { ProfessorService } from './../service/professor.service';
import { TurmaService } from './../service/turma.service';
import { ErrorHandleService } from '../service/error-handle.service';
import { NavbarComponent } from './navbar/navbar.component';
import { EnderecoService } from '../service/endereco.service';
import { AlunoService } from '../service/aluno.service';

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
    AuthService,
    JwtHelper,
    DisciplinaService,
    TurmaService,
    ProfessorService,
    AlunoService,
    ErrorHandleService,
    EnderecoService,
  ],
  declarations: [NavbarComponent, PaginaNaoEncontradaComponent]
})
export class CoreModule { }
