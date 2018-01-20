import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { ButtonModule } from 'primeng/components/button/button';
import { DataTableModule } from 'primeng/components/datatable/datatable';
import { DialogModule } from 'primeng/primeng';
import { TooltipModule } from 'primeng/components/tooltip/tooltip';
import { FieldsetModule } from 'primeng/components/fieldset/fieldset';

import { DisciplinaPesquisaComponent } from './disciplina-pesquisa/disciplina-pesquisa.component';

/**LAZY LOAD - só é carregado quando solicitado */
const ROUTES: Routes = [
  { path: '', component: DisciplinaPesquisaComponent }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    InputTextModule,
    ButtonModule,
    DataTableModule,
    DialogModule,
    TooltipModule,
    FieldsetModule,

    RouterModule.forChild(ROUTES),
  ],
  declarations: [
    DisciplinaPesquisaComponent
  ],
})
export class DisciplinaModule { }
