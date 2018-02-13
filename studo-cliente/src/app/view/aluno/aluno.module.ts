import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { DropdownModule } from 'primeng/components/dropdown/dropdown';
import { ButtonModule } from 'primeng/components/button/button';
import { CalendarModule } from 'primeng/components/calendar/calendar';
import { SelectButtonModule } from 'primeng/components/selectbutton/selectbutton';

import { AlunoCadastroComponent } from './aluno-cadastro/aluno-cadastro.component';

import { SharedModule } from './../../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,

    InputTextModule,
    DropdownModule,
    ButtonModule,
    CalendarModule,
    SelectButtonModule,

    SharedModule
  ],
  declarations: [AlunoCadastroComponent]
})
export class AlunoModule { }
