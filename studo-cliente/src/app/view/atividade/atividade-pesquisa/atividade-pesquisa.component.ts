import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';

import { CalendarioUtil } from './../../../util/calendario.util';
import { LazyLoadEvent } from 'primeng/components/common/lazyloadevent';

import { ErrorHandleService } from './../../../service/error-handle.service';
import { AtividadeService, FiltroAtividade } from './../../../service/atividade.service';

@Component({
  selector: 'app-atividade-pesquisa',
  templateUrl: './atividade-pesquisa.component.html',
  styleUrls: ['./atividade-pesquisa.component.css']
})
export class AtividadePesquisaComponent implements OnInit {

  pt: any;
  totalRegistros = 0;
  filtro = new FiltroAtividade();
  atividades = [];
  @ViewChild('tabela') grid;

  constructor(
    private atividadeService: AtividadeService,
    private errorHandle: ErrorHandleService,
  ) { }

  ngOnInit() {
    this.traduzirCalendar();
  }

  traduzirCalendar() {
    this.pt = CalendarioUtil.pt;
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.atividadeService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.atividades = resultado.atividades;
      }).catch(erro => this.errorHandle.handle(erro));
  }

  mudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  limpa(form: FormControl) {
    form.reset();
    this.grid.reset();
    this.pesquisar();
  }

}
