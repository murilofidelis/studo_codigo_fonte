import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastyModule } from 'ng2-toasty';

import { DisciplinaService } from '../service/disciplina.service';
import { ErrorHandleService } from '../service/error-handle.service';
import { MessageComponent } from './message/message.component';

@NgModule({
  imports: [
    CommonModule,
    ToastyModule.forRoot()
  ],
  exports: [
    ToastyModule,
    MessageComponent
  ],
  providers: [
    DisciplinaService,
    ErrorHandleService],
  declarations: [MessageComponent]
})
export class CoreModule { }
