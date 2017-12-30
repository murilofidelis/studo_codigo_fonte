import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DisciplinaPesquisaComponent } from './disciplina-pesquisa/disciplina-pesquisa.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    DisciplinaPesquisaComponent
  ],
  exports: [
    DisciplinaPesquisaComponent
  ]
})
export class DisciplinaModule { }
