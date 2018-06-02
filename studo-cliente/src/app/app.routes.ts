import { DocumentosComponent } from './view/aluno/documentos/documentos.component';

import { Routes } from '@angular/router';

import { AuthGuard } from './seguranca/auth.guard';

import { DashbordComponent } from './view/dashbord/dashbord/dashbord.component';
import { DisciplinaPesquisaComponent } from './view/disciplina/disciplinas/disciplina-pesquisa.component';
import { TurmaPesquisaComponent } from './view/turma/turma-pesquisa/turma-pesquisa.component';
import { TurmaCadastroComponent } from './view/turma/turma-cadastro/turma-cadastro.component';
import { ProfessorPesquisaComponent } from './view/professor/professor-pesquisa/professor-pesquisa.component';
import { ProfessorCadastroComponent } from './view/professor/professor-cadastro/professor-cadastro.component';
import { PaginaNaoEncontradaComponent } from './core/pagina-nao-encontrada/pagina-nao-encontrada.component';
import { AlunoCadastroComponent } from './view/aluno/aluno-cadastro/aluno-cadastro.component';
import { AlunoPesquisaComponent } from './view/aluno/aluno-pesquisa/aluno-pesquisa.component';
import { MatriculaComponent } from './view/aluno/matricula/matricula.component';
import { AcessoNegadoComponent } from './core/acesso-negado/acesso-negado.component';
import { AtividadePesquisaComponent } from './view/atividade/atividade-pesquisa/atividade-pesquisa.component';
import { AtividadeCadastroComponent } from './view/atividade/atividade-cadastro/atividade-cadastro.component';

export const ROUTES: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  {
    path: 'home', component: DashbordComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_VISUALIZAR_DASHBORD'] }
  },
  {
    path: 'aluno', component: AlunoPesquisaComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_LISTAR_ALUNO'] }
  },
  {
    path: 'aluno/novo', component: AlunoCadastroComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_ALUNO'] }
  },
  {
    path: 'aluno/:codigo', component: AlunoCadastroComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_ALTERAR_ALUNO'] }
  },
  {
    path: 'aluno/documentos/:codigo', component: DocumentosComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_ALTERAR_ALUNO'] }
  },
  {
    path: 'matricula/:codigo', component: MatriculaComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_ALTERAR_ALUNO'] }
  },
  {
    path: 'turma', component: TurmaPesquisaComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_LISTAR_TURMA'] }
  },
  {
    path: 'turma/nova', component: TurmaCadastroComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_TURMA'] }
  },
  {
    path: 'turma/:codigo', component: TurmaCadastroComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_ALTERAR_TURMA'] }
  },
  {
    path: 'diciplina', loadChildren: './view/disciplina/disciplina.module#DisciplinaModule', canActivate: [AuthGuard],
    data: { roles: ['ROLE_LISTAR_DISCIPLINA'] }
  },
  {
    path: 'professor', component: ProfessorPesquisaComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_LISTAR_PROFESSOR'] }
  },
  {
    path: 'professor/novo', component: ProfessorCadastroComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_PROFESSOR'] }
  },
  {
    path: 'professor/:codigo', component: ProfessorCadastroComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_ALTERAR_PROFESSOR'] }
  },
  {
    path: 'atividade', component: AtividadePesquisaComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_LISTAR_ATIVIDADE'] }
  },
  {
    path: 'atividade/nova', component: AtividadeCadastroComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_ATIVIDADE'] }
  },
  {
    path: 'atividade/:codigo', component: AtividadeCadastroComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_ALTERAR_ATIVIDADE'] }
  },



  { path: 'pagina-nao-encontrada', component: PaginaNaoEncontradaComponent },
  { path: 'acesso-negado', component: AcessoNegadoComponent },

  { path: '**', redirectTo: 'pagina-nao-encontrada' }
];

