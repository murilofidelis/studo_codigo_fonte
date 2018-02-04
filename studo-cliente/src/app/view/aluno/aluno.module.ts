import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { DropdownModule } from 'primeng/components/dropdown/dropdown';
import { ButtonModule } from 'primeng/components/button/button';


import { AlunoCadastroComponent } from './aluno-cadastro/aluno-cadastro.component';
import { TurmaInfoComponent } from './aluno-cadastro/turma-info/turma-info.component';

@NgModule({
  imports: [
    CommonModule,
    InputTextModule,
    DropdownModule,
    ButtonModule
  ],
  declarations: [AlunoCadastroComponent, TurmaInfoComponent]
})
export class AlunoModule { }
