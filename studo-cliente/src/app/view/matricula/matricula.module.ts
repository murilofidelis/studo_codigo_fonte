import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';

import { MatriculaCadastroComponent } from './matricula-cadastro/matricula-cadastro.component';
import { TurmaInfoComponent } from './matricula-cadastro/turma-info/turma-info.component';

@NgModule({
  imports: [
    CommonModule,

    InputTextModule
  ],
  declarations: [MatriculaCadastroComponent, TurmaInfoComponent]
})
export class MatriculaModule { }
