import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { DropdownModule } from 'primeng/components/dropdown/dropdown';
import { ButtonModule } from 'primeng/components/button/button';
import { SelectButtonModule } from 'primeng/components/selectbutton/selectbutton';
import { InputMaskModule } from 'primeng/components/inputmask/inputmask';

import { ProfessorCadastroComponent } from './professor-cadastro/professor-cadastro.component';

@NgModule({
  imports: [
    CommonModule,

    InputTextModule,
    DropdownModule,
    ButtonModule,
    SelectButtonModule,
    InputMaskModule
  ],
  declarations: [ProfessorCadastroComponent]
})
export class ProfessorModule { }
