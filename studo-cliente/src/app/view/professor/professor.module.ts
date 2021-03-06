import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { DropdownModule } from 'primeng/components/dropdown/dropdown';
import { ButtonModule } from 'primeng/components/button/button';
import { SelectButtonModule } from 'primeng/components/selectbutton/selectbutton';
import { InputMaskModule } from 'primeng/components/inputmask/inputmask';
import { FieldsetModule } from 'primeng/components/fieldset/fieldset';
import { DataTableModule } from 'primeng/components/datatable/datatable';
import { TooltipModule } from 'primeng/components/tooltip/tooltip';

import { ProfessorPesquisaComponent } from './professor-pesquisa/professor-pesquisa.component';
import { ProfessorCadastroComponent } from './professor-cadastro/professor-cadastro.component';
import { SharedModule } from '../../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,

    InputTextModule,
    DropdownModule,
    ButtonModule,
    SelectButtonModule,
    InputMaskModule,
    FieldsetModule,
    DataTableModule,
    TooltipModule,

    SharedModule,
  ],
  declarations: [ProfessorCadastroComponent, ProfessorPesquisaComponent]
})
export class ProfessorModule { }
