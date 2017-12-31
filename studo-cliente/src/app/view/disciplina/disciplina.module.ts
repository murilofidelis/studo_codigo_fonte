import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { ButtonModule } from 'primeng/components/button/button';
import { DataTableModule } from 'primeng/components/datatable/datatable';
import { DialogModule } from 'primeng/primeng';
import { TooltipModule } from 'primeng/components/tooltip/tooltip';

import { DisciplinaPesquisaComponent } from './disciplina-pesquisa/disciplina-pesquisa.component';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    InputTextModule,
    ButtonModule,
    DataTableModule,
    DialogModule,
    TooltipModule
  ],
  declarations: [
    DisciplinaPesquisaComponent
  ],
  exports: [
    DisciplinaPesquisaComponent
  ]
})
export class DisciplinaModule { }
