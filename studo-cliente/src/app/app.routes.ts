import { Routes } from '@angular/router';

import { DisciplinaPesquisaComponent } from './view/disciplina/disciplina-pesquisa/disciplina-pesquisa.component';
import { TurmaPesquisaComponent } from './view/turma/turma-pesquisa/turma-pesquisa.component';
import { TurmaCadastroComponent } from './view/turma/turma-cadastro/turma-cadastro.component';

export const ROUTES: Routes = [
  { path: '', redirectTo: 'turmas', pathMatch: 'full' },
  { path: 'turmas', component: TurmaPesquisaComponent },
  { path: 'turmas/nova', component: TurmaCadastroComponent },
  { path: 'turmas/:codigo', component: TurmaCadastroComponent },
  { path: 'diciplinas', loadChildren: './view/disciplina/disciplina.module#DisciplinaModule' }
];

