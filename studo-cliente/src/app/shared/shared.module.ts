import { EnderecoComponent } from './endereco/endereco.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { InputMaskModule } from 'primeng/components/inputmask/inputmask';

import { MessageComponent } from './message/message.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    InputTextModule,
    InputMaskModule

  ],
  exports: [MessageComponent, EnderecoComponent],
  declarations: [MessageComponent, EnderecoComponent]
})
export class SharedModule { }
