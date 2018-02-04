import { Component, OnInit } from '@angular/core';

import { TurmaService } from './../../../../service/turma.service';
import { Turma } from '../../../../model/turma.model';
import { ErrorHandleService } from '../../../../service/error-handle.service';

@Component({
  selector: 'app-turma-info',
  templateUrl: './turma-info.component.html',
  styleUrls: ['./turma-info.component.css']
})
export class TurmaInfoComponent implements OnInit {

  turma: Turma = new Turma();

  constructor(
    private turmaService: TurmaService,
    private errorHandle: ErrorHandleService) { }

  ngOnInit() {
  }

  buscarPorNumero(event) {
    console.log(event.target.value);
    this.turmaService.buscarPorNumero(event.target.value).then(resultado => {
      this.turma = resultado;
      console.log(this.turma);
    }).catch(erro => this.errorHandle.handle(erro));

  }

}
