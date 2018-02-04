import { ProfessorService } from './../service/professor.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ToastyModule } from 'ng2-toasty';

import { DisciplinaService } from '../service/disciplina.service';
import { TurmaService } from './../service/turma.service';
import { ErrorHandleService } from '../service/error-handle.service';
import { NavbarComponent } from './navbar/navbar.component';
import { EnderecoService } from '../service/endereco.service';
import { PaginaNaoEncontradaComponent } from './pagina-nao-encontrada/pagina-nao-encontrada.component';


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
    ProfessorService,
    ErrorHandleService,
    EnderecoService,
  ],
  declarations: [NavbarComponent, PaginaNaoEncontradaComponent]
})
export class CoreModule { }
