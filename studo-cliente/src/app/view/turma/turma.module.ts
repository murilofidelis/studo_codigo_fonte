import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { DropdownModule } from 'primeng/components/dropdown/dropdown';
import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { ButtonModule } from 'primeng/components/button/button';
import { DataTableModule } from 'primeng/components/datatable/datatable';
import { TooltipModule } from 'primeng/components/tooltip/tooltip';
import { FieldsetModule } from 'primeng/components/fieldset/fieldset';

import { TurmaCadastroComponent } from './turma-cadastro/turma-cadastro.component';
import { TurmaPesquisaComponent } from './turma-pesquisa/turma-pesquisa.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,

    DropdownModule,
    InputTextModule,
    ButtonModule,
    DataTableModule,
    TooltipModule,
    FieldsetModule
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
