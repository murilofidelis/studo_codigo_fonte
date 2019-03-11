import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ToastyModule } from 'ng2-toasty';
import { JwtHelper } from 'angular2-jwt';

import { PaginaNaoEncontradaComponent } from './pagina-nao-encontrada/pagina-nao-encontrada.component';
import { AcessoNegadoComponent } from './acesso-negado/acesso-negado.component';

import { AuthService } from '../service/auth.service';
import { DisciplinaService } from '../service/disciplina.service';
import { ProfessorService } from './../service/professor.service';
import { TurmaService } from './../service/turma.service';
import { ErrorHandleService } from '../service/error-handle.service';
import { NavbarComponent } from './navbar/navbar.component';
import { EnderecoService } from '../service/endereco.service';
import { AlunoService } from '../service/aluno.service';
import { DashbordService } from './../service/dashbord.service';
import { AtividadeService } from '../service/atividade.service';
import { DocumentosService } from './../service/documentos.service';
import { ConfirmationService } from 'primeng/primeng';

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
    DashbordService,
    AtividadeService,
    DocumentosService,
    ConfirmationService
  ],
  declarations: [NavbarComponent, PaginaNaoEncontradaComponent, AcessoNegadoComponent]
})
export class CoreModule { }
