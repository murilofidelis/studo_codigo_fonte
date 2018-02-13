import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { DropdownModule } from 'primeng/components/dropdown/dropdown';
import { ButtonModule } from 'primeng/components/button/button';
import { CalendarModule } from 'primeng/components/calendar/calendar';
import { SelectButtonModule } from 'primeng/components/selectbutton/selectbutton';
import { FieldsetModule } from 'primeng/components/fieldset/fieldset';
import { DataTableModule } from 'primeng/components/datatable/datatable';
import { TooltipModule } from 'primeng/components/tooltip/tooltip';

import { AlunoCadastroComponent } from './aluno-cadastro/aluno-cadastro.component';

import { SharedModule } from './../../shared/shared.module';
import { AlunoPesquisaComponent } from './aluno-pesquisa/aluno-pesquisa.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,

    InputTextModule,
    DropdownModule,
    ButtonModule,
    CalendarModule,
    SelectButtonModule,
    FieldsetModule,
    DataTableModule,
    TooltipModule,

    SharedModule
  ],
  declarations: [AlunoCadastroComponent, AlunoPesquisaComponent]
})
export class AlunoModule { }
