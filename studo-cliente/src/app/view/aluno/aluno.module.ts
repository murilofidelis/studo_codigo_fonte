import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { DropdownModule } from 'primeng/components/dropdown/dropdown';
import { ButtonModule } from 'primeng/components/button/button';
import { CalendarModule } from 'primeng/components/calendar/calendar';

import { AlunoCadastroComponent } from './aluno-cadastro/aluno-cadastro.component';
import { TurmaInfoComponent } from './aluno-cadastro/turma-info/turma-info.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    FormsModule,

    InputTextModule,
    DropdownModule,
    ButtonModule,
    CalendarModule
  ],
  declarations: [AlunoCadastroComponent, TurmaInfoComponent]
})
export class AlunoModule { }
