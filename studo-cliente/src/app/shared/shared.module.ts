import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MessageComponent } from './message/message.component';
import { EnderecoComponent } from './endereco/endereco.component';

@NgModule({
  imports: [
    CommonModule
  ],
  exports: [MessageComponent],
  declarations: [MessageComponent, EnderecoComponent]
})
export class SharedModule { }
