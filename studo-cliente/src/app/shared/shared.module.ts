import { EnderecoComponent } from './endereco/endereco.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { InputMaskModule } from 'primeng/components/inputmask/inputmask';

import { MessageComponent } from './message/message.component';
import { FormDebugComponent } from '../util/form-debug/form-debug.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    InputTextModule,
    InputMaskModule

  ],
  exports: [MessageComponent, EnderecoComponent, FormDebugComponent],
  declarations: [MessageComponent, EnderecoComponent, FormDebugComponent]
})
export class SharedModule { }
