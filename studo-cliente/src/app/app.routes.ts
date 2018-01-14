import { Routes } from '@angular/router';

import { DisciplinaPesquisaComponent } from './view/disciplina/disciplina-pesquisa/disciplina-pesquisa.component';
import { TurmaPesquisaComponent } from './view/turma/turma-pesquisa/turma-pesquisa.component';

export const ROUTES: Routes = [
  { path: '', redirectTo: 'turmas', pathMatch: 'full' },
  { path: 'turmas', component: TurmaPesquisaComponent },
  { path: 'diciplinas', loadChildren: './view/disciplina/disciplina.module#DisciplinaModule' }
];

