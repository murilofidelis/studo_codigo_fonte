import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/components/common/api';

import { TurmaService, TurmaFiltro } from './../../../service/turma.service';
import { ErrorHandleService } from './../../../service/error-handle.service';
import { ToastyService } from 'ng2-toasty';
import { LazyLoadEvent } from 'primeng/components/common/lazyloadevent';

@Component({
  selector: 'app-turma-pesquisa',
  templateUrl: './turma-pesquisa.component.html',
  styleUrls: ['./turma-pesquisa.component.css']
})
export class TurmaPesquisaComponent implements OnInit {

  totalRegistros = 0;

  periodos: SelectItem[];
  filtro = new TurmaFiltro();
  turmas = [];

  constructor(
    private turmasService: TurmaService,
    private errorHandle: ErrorHandleService,
    private toasty: ToastyService,
  ) { }

  ngOnInit() {
    this.iniciaTurma();
  }

  iniciaTurma() {
    this.periodos = [];
    this.periodos.push({ label: 'Selecione...', value: null });
    this.periodos.push({ label: 'Matutino', value: 'MATUTINO' });
    this.periodos.push({ label: 'Vespertino', value: 'VESPERTINO' });
    this.periodos.push({ label: 'Noturno', value: 'NOTURNO' });
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.turmasService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.turmas = resultado.turmas;
      }).catch(erro => this.errorHandle.handle(erro));
  }
  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);

  }

}
