import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from './../../shared/shared.module';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { ButtonModule } from 'primeng/components/button/button';
import { DataTableModule } from 'primeng/components/datatable/datatable';
import { DialogModule } from 'primeng/components/dialog/dialog';
import { TooltipModule } from 'primeng/components/tooltip/tooltip';
import { FieldsetModule } from 'primeng/components/fieldset/fieldset';
import { CalendarModule } from 'primeng/components/calendar/calendar';
import { DropdownModule } from 'primeng/components/dropdown/dropdown';
import { SelectButtonModule } from 'primeng/components/selectbutton/selectbutton';
import { InputTextareaModule } from 'primeng/components/inputtextarea/inputtextarea';
import { ConfirmDialogModule } from 'primeng/components/confirmdialog/confirmdialog';

import { AtividadePesquisaComponent } from './atividade-pesquisa/atividade-pesquisa.component';
import { AtividadeCadastroComponent } from './atividade-cadastro/atividade-cadastro.component';
import { ClassificacaoDisciplinaPipe } from '../../util/pipes/classificacao-disciplina.pipe';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    FormsModule,

    InputTextModule,
    ButtonModule,
    DataTableModule,
    DialogModule,
    TooltipModule,
    FieldsetModule,
    CalendarModule,
    DropdownModule,
    SelectButtonModule,
    InputTextareaModule,
    ConfirmDialogModule,
    SharedModule
  ],
  declarations: [AtividadePesquisaComponent, AtividadeCadastroComponent, ClassificacaoDisciplinaPipe],
})
export class AtividadeModule { }
