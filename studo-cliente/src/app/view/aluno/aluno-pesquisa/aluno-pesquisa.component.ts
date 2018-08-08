import { Component, ViewChild, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { ToastyService } from 'ng2-toasty';
import { LazyLoadEvent } from 'primeng/components/common/lazyloadevent';

import { ErrorHandleService } from './../../../service/error-handle.service';
import { AlunoService, AlunoFiltro } from './../../../service/aluno.service';
import { Pageable } from '../../../util/pageable';

@Component({
  selector: 'app-aluno-pesquisa',
  templateUrl: './aluno-pesquisa.component.html',
  styleUrls: ['./aluno-pesquisa.component.css']
})
export class AlunoPesquisaComponent implements OnInit {

  totalRegistros = 0;
  filtro = new AlunoFiltro();
  alunos = [];
  sexo = [];
  status = [];
  @ViewChild('tabela') grid;

  constructor(
    private alunoService: AlunoService,
    private errorHandle: ErrorHandleService,
    private toasty: ToastyService,
  ) { }

  ngOnInit() {
    this.iniciaSexo();
    this.iniciaStatus();
  }

  iniciaSexo() {
    this.sexo = [];
    this.sexo.push({ label: 'Selecione...', value: null });
    this.sexo.push({ label: 'Masculino', value: 'MASCULINO' });
    this.sexo.push({ label: 'Feminino', value: 'FEMININO' });
  }

  iniciaStatus() {
    this.status.push({ label: 'Ativo', value: true });
    this.status.push({ label: 'Inativo', value: false });
  }

  pesquisar(event: LazyLoadEvent) {

    const pageable = new Pageable(0, 10, 'codigo', 'ASC');

    if (event) {
      pageable.size = event.rows || 10;
      pageable.page = event.first / event.rows || 0;
      pageable.sortField = event.sortField ? event.sortField : 'codigo';
      pageable.sortOrder = event.sortOrder === 1 ? 'DESC' : 'ASC';
    }

    this.alunoService.filtar(this.filtro, pageable)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.alunos = resultado.alunos;
      }).catch(erro => this.errorHandle.handle(erro));
  }

  limpa(form: FormControl) {
    form.reset();
    this.grid.reset();
    this.pesquisar(null);
  }

  geraRelatorio() {
    this.alunoService.downloadRelatorio().subscribe(blob => {
      this.downloadArquivo(blob, 'relatorio.pdf');
    });
  }

  private downloadArquivo(blob: any, nomeArquivo: string) {
    const url = window.URL.createObjectURL(blob);
    const element = window.document.createElement('a');
    document.body.appendChild(element);

    element.href = url;
    element.download = nomeArquivo;
    element.click();
    document.body.removeChild(element);
  }

}
