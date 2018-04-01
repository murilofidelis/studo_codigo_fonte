import { Component, OnInit } from '@angular/core';

import { CalendarioUtil } from './../../../util/calendario.util';

@Component({
  selector: 'app-atividade-pesquisa',
  templateUrl: './atividade-pesquisa.component.html',
  styleUrls: ['./atividade-pesquisa.component.css']
})
export class AtividadePesquisaComponent implements OnInit {

  pt: any;

  constructor() { }

  ngOnInit() {
    this.traduzirCalendar();
  }

  traduzirCalendar() {
    this.pt = CalendarioUtil.pt;
  }
}
