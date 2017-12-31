import { Component, ViewChild } from '@angular/core';
import { LazyLoadEvent } from 'primeng/components/common/api';
import { DisciplinaService, DisciplinaFiltro } from '../../../service/disciplina.service';

@Component({
  selector: 'app-disciplina-pesquisa',
  templateUrl: './disciplina-pesquisa.component.html',
  styleUrls: ['./disciplina-pesquisa.component.css']
})
export class DisciplinaPesquisaComponent {

  totalRegistros = 0;
  filtro = new DisciplinaFiltro();
  disciplinas = [];
  @ViewChild('tabela') grid;

  constructor(private disciplinaService: DisciplinaService) { }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.disciplinaService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.disciplinas = resultado.disciplinas;
      });
  }

  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }
}
