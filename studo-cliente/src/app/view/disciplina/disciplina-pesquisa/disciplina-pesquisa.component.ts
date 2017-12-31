import { Component, ViewChild } from '@angular/core';
import { LazyLoadEvent } from 'primeng/components/common/api';
import { DisciplinaService, DisciplinaFiltro } from '../../../service/disciplina.service';
import { Disciplina } from '../../../model/disciplina.model';

@Component({
  selector: 'app-disciplina-pesquisa',
  templateUrl: './disciplina-pesquisa.component.html',
  styleUrls: ['./disciplina-pesquisa.component.css']
})
export class DisciplinaPesquisaComponent {

  disciplina = new Disciplina();
  totalRegistros = 0;
  filtro = new DisciplinaFiltro();
  disciplinas = [];
  @ViewChild('tabela') grid;
  display: boolean;

  constructor(private disciplinaService: DisciplinaService) { }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.disciplinaService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.disciplinas = resultado.disciplinas;
      });
  }

  mudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  novaDisciplina() {
    this.display = true;
  }
  cancelaCadastro() {
    this.display = false;
  }

  salvar() {
    this.disciplina.ativa = true;
    this.disciplinaService.salvar(this.disciplina).then(() => {
      this.display = false;
      this.disciplina = new Disciplina();
      this.pesquisar();
    });
  }
}
