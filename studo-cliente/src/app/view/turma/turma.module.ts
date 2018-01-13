import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { DropdownModule } from 'primeng/components/dropdown/dropdown';
import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { ButtonModule } from 'primeng/components/button/button';

import { TurmaCadastroComponent } from './turma-cadastro/turma-cadastro.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    DropdownModule,
    InputTextModule,
    ButtonModule
  ],
  declarations: [
    TurmaCadastroComponent
  ],
  exports: [
    TurmaCadastroComponent
  ]
})
export class TurmaModule { }
