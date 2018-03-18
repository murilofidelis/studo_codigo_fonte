import { Routes } from '@angular/router';

import { AuthGuard } from './seguranca/auth.guard';

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

export const ROUTES: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  { path: 'aluno', component: AlunoPesquisaComponent },
  { path: 'aluno/novo', component: AlunoCadastroComponent, canActivate: [AuthGuard] },
  { path: 'aluno/:codigo', component: AlunoCadastroComponent },

  { path: 'matricula/:codigo', component: MatriculaComponent },

  { path: 'turmas', component: TurmaPesquisaComponent },
  { path: 'turmas/nova', component: TurmaCadastroComponent },
  { path: 'turmas/:codigo', component: TurmaCadastroComponent },

  {
    path: 'diciplinas', loadChildren: './view/disciplina/disciplina.module#DisciplinaModule', canActivate: [AuthGuard],
    data: { roles: ['ROLE_LISTAR_DISCIPLINAS'] }
  },

  { path: 'professor', component: ProfessorPesquisaComponent },
  { path: 'professor/novo', component: ProfessorCadastroComponent },
  { path: 'professor/:codigo', component: ProfessorCadastroComponent },

  { path: 'pagina-nao-encontrada', component: PaginaNaoEncontradaComponent },
  { path: 'acesso-negado', component: AcessoNegadoComponent },

  { path: '**', redirectTo: 'pagina-nao-encontrada' }
];

