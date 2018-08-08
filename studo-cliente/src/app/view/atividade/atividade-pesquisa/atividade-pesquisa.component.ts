import { Mensagem } from './../../../util/mensagens.util';
import { ToastyService } from 'ng2-toasty';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';

import { CalendarioUtil } from './../../../util/calendario.util';
import { LazyLoadEvent } from 'primeng/components/common/lazyloadevent';
import { ConfirmationService } from 'primeng/components/common/api';

import { DisciplinaService } from '../../../service/disciplina.service';
import { AtividadeService, FiltroAtividade } from './../../../service/atividade.service';
import { Atividade } from './../../../model/atividade.model';
import { Pageable } from '../../../util/pageable';

@Component({
  selector: 'app-atividade-pesquisa',
  templateUrl: './atividade-pesquisa.component.html',
  styleUrls: ['./atividade-pesquisa.component.css'],
  providers: [ConfirmationService]
})
export class AtividadePesquisaComponent implements OnInit {

  pt: any;
  totalRegistros = 0;
  filtro = new FiltroAtividade();
  atividades = [];
  disciplinas = [];
  visualisarAtividade: boolean;
  atividade: Atividade;
  @ViewChild('tabela') grid;

  constructor(
    private atividadeService: AtividadeService,
    private disciplinaService: DisciplinaService,
    private confirmartion: ConfirmationService,
    private toasty: ToastyService
  ) { }

  ngOnInit() {
    this.traduzirCalendar();
    this.carregarDiscicplinas();
  }

  traduzirCalendar() {
    this.pt = CalendarioUtil.pt;
  }

  carregarDiscicplinas() {
    this.disciplinas = [];
    this.disciplinas.push({ label: 'Selecione...', value: null });
    this.disciplinaService.buscaTodas().then(disciplinas => {
      disciplinas.forEach(disciplina => {
        this.disciplinas.push({ label: disciplina.descricao, value: disciplina.codigo });
      });
    });
  }

  pesquisar(event: LazyLoadEvent) {

    const pageable = new Pageable(0, 10, 'codigo', 'ASC');

    if (event) {
      pageable.size = event.rows || 10;
      pageable.page = event.first / event.rows || 0;
      pageable.sortField = event.sortField ? event.sortField : 'codigo';
      pageable.sortOrder = event.sortOrder === 1 ? 'DESC' : 'ASC';
    }

    this.atividadeService.filtrar(this.filtro, pageable).subscribe(resultado => {
      this.totalRegistros = resultado.total;
      this.atividades = resultado.atividades;
    });
  }

  limpa(form: FormControl) {
    form.reset();
    this.grid.reset();
    this.pesquisar(null);
  }

  excluirAtividade(atividade: any) {
    this.confirmartion.confirm({
      message: `Deseja excluir esta atividade ?`,
      header: 'Excluir Atividade',
      icon: 'fa fa-question-circle',
      accept: () => {
        this.excluir(atividade);
      },
      reject: () => { }
    });
  }

  excluir(atividade: any) {
    this.atividadeService.exluirAtividade(atividade.codigo).then(res => {
      if (res) {
        this.toasty.info(Mensagem.MENSAGEM_EXCLUIDO_SUCESSO);
        this.pesquisar(null);
      }
    });
  }

  exibirAtividade(atividade: Atividade) {
    this.visualisarAtividade = true;
    this.atividade = atividade;
  }
}
