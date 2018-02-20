import { Component, Output, EventEmitter } from '@angular/core';

import { TurmaService } from './../../../../service/turma.service';
import { Turma } from '../../../../model/turma.model';
import { ErrorHandleService } from '../../../../service/error-handle.service';

@Component({
  selector: 'app-turma-info',
  templateUrl: './turma-info.component.html',
  styleUrls: ['./turma-info.component.css']
})
export class TurmaInfoComponent {

  turma: Turma = new Turma();

  @Output() turmaAtualizada = new EventEmitter();

  constructor(
    private turmaService: TurmaService,
    private errorHandle: ErrorHandleService) { }

  buscarPorNumero(event) {
    this.turmaService.buscarPorNumero(event.target.value).then(resultado => {
      this.turma = resultado;
      this.turmaAtualizada.emit(this.turma);
    }).catch(erro => this.errorHandle.handle(erro));
  }

}
