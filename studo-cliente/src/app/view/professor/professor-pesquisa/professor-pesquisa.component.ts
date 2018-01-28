import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';

import { ToastyService } from 'ng2-toasty';
import { LazyLoadEvent } from 'primeng/components/common/lazyloadevent';

import { ProfessorService, ProfessorFiltro } from '../../../service/professor.service';
import { ErrorHandleService } from './../../../service/error-handle.service';

@Component({
  selector: 'app-professor-pesquisa',
  templateUrl: './professor-pesquisa.component.html',
  styleUrls: ['./professor-pesquisa.component.css']
})
export class ProfessorPesquisaComponent implements OnInit {

  totalRegistros = 0;
  filtro = new ProfessorFiltro();
  professores = [];
  @ViewChild('tabela') grid;

  constructor(
    private professorService: ProfessorService,
    private errorHandle: ErrorHandleService,
    private toasty: ToastyService,
  ) { }

  ngOnInit() {
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.professorService.pesuisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.professores = resultado.professores;
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
