import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { DropdownModule } from 'primeng/components/dropdown/dropdown';
import { ButtonModule } from 'primeng/components/button/button';
import { SelectButtonModule } from 'primeng/components/selectbutton/selectbutton';
import { InputMaskModule } from 'primeng/components/inputmask/inputmask';

import { ProfessorCadastroComponent } from './professor-cadastro/professor-cadastro.component';
import { SharedModule } from '../../shared/shared.module';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    InputTextModule,
    DropdownModule,
    ButtonModule,
    SelectButtonModule,
    InputMaskModule,

    SharedModule,
  ],
  declarations: [ProfessorCadastroComponent]
})
export class ProfessorModule { }
