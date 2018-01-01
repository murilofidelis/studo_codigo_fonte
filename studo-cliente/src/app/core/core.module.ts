import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastyModule } from 'ng2-toasty';

import { DisciplinaService } from '../service/disciplina.service';
import { ErrorHandleService } from '../service/error-handle.service';

@NgModule({
  imports: [
    CommonModule,
    ToastyModule.forRoot()
  ],
  exports: [
    ToastyModule,
  ],
  providers: [
    DisciplinaService,
    ErrorHandleService],
  declarations: []
})
export class CoreModule { }
