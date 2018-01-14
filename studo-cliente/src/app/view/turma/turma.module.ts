import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { DropdownModule } from 'primeng/components/dropdown/dropdown';
import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { ButtonModule } from 'primeng/components/button/button';
import { DataTableModule } from 'primeng/components/datatable/datatable';
import { TooltipModule } from 'primeng/components/tooltip/tooltip';

import { TurmaCadastroComponent } from './turma-cadastro/turma-cadastro.component';
import { TurmaPesquisaComponent } from './turma-pesquisa/turma-pesquisa.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    DropdownModule,
    InputTextModule,
    ButtonModule,
    DataTableModule,
    TooltipModule
  ],
  declarations: [
    TurmaCadastroComponent,
    TurmaPesquisaComponent
  ],
  exports: [
    TurmaCadastroComponent,
    TurmaPesquisaComponent
  ]
})
export class TurmaModule { }
