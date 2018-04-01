import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { ButtonModule } from 'primeng/components/button/button';
import { DataTableModule } from 'primeng/components/datatable/datatable';
import { DialogModule } from 'primeng/primeng';
import { TooltipModule } from 'primeng/components/tooltip/tooltip';
import { FieldsetModule } from 'primeng/components/fieldset/fieldset';
import { CalendarModule } from 'primeng/components/calendar/calendar';

import { AtividadePesquisaComponent } from './atividade-pesquisa/atividade-pesquisa.component';
import { AtividadeCadastroComponent } from './atividade-cadastro/atividade-cadastro.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,

    FormsModule,
    InputTextModule,
    ButtonModule,
    DataTableModule,
    DialogModule,
    TooltipModule,
    FieldsetModule,
    CalendarModule,
  ],
  declarations: [AtividadePesquisaComponent, AtividadeCadastroComponent]
})
export class AtividadeModule { }
