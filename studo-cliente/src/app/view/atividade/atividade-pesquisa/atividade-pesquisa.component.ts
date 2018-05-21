import { Mensagem } from './../../../util/mensagens.util';
import { ToastyService } from 'ng2-toasty';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';

import { CalendarioUtil } from './../../../util/calendario.util';
import { LazyLoadEvent } from 'primeng/components/common/lazyloadevent';
import { ConfirmationService } from 'primeng/components/common/api';

import { ErrorHandleService } from './../../../service/error-handle.service';
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
  @ViewChild('tabela') grid;

  constructor(
    private atividadeService: AtividadeService,
    private errorHandle: ErrorHandleService,
    private confirmartion: ConfirmationService,
    private toasty: ToastyService
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
}
