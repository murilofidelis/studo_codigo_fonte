import { Mensagem } from './../../../util/mensagens.util';
import { ToastyService } from 'ng2-toasty';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';

import { CalendarioUtil } from './../../../util/calendario.util';
import { LazyLoadEvent } from 'primeng/components/common/lazyloadevent';
import { ConfirmationService } from 'primeng/components/common/api';

import { ErrorHandleService } from './../../../service/error-handle.service';
import { DisciplinaService } from '../../../service/disciplina.service';
import { AtividadeService, FiltroAtividade } from './../../../service/atividade.service';
import { Atividade } from './../../../model/atividade.model';

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
    private errorHandle: ErrorHandleService,
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

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.atividadeService.filtrar(this.filtro).subscribe(resultado => {
      console.log('RESULTADO, ', resultado);
      this.totalRegistros = resultado.total;
      this.atividades = resultado.atividades;
    });
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
        this.pesquisar();
      }
    });
  }

  exibirAtividade(atividade: Atividade) {
    this.visualisarAtividade = true;
    this.atividade = atividade;
  }
}
