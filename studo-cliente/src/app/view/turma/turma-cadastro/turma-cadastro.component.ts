import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/components/common/api';
import { ToastyService } from 'ng2-toasty';

import { TurmaService } from './../../../service/turma.service';
import { Turma } from './../../../model/turma.model';
import { ErrorHandleService } from '../../../service/error-handle.service';
import { Mensagem } from './../../../model/mensagens.model';

@Component({
  selector: 'app-turma-cadastro',
  templateUrl: './turma-cadastro.component.html',
  styleUrls: ['./turma-cadastro.component.css']
})
export class TurmaCadastroComponent implements OnInit {

  periodos: SelectItem[];
  series: SelectItem[];
  turmaForm: FormGroup;

  turma = new Turma();

  constructor(
    private activatedRoute: ActivatedRoute,
    private route: Router,
    private formBuilder: FormBuilder,
    private turmaService: TurmaService,
    private toasty: ToastyService,
    private errorHandle: ErrorHandleService
  ) { }

  ngOnInit() {
    const codigoTurma = this.activatedRoute.snapshot.params['codigo'];

    this.turmaForm = this.formBuilder.group({
      codigo: this.formBuilder.control(null),
      periodo: this.formBuilder.control(null, Validators.required),
      serie: this.formBuilder.control(null, Validators.required),
      descricaoTurma: this.formBuilder.control(null, [Validators.required, Validators.maxLength(1)]),
      sala: this.formBuilder.control(null, Validators.maxLength(4)),
      ano: this.formBuilder.control(null, [Validators.required, Validators.maxLength(4)]),
    });

    if (codigoTurma) {
      this.carregarTurma(codigoTurma);
    }
    this.iniciaTurma();
    this.iniciaSerie();
  }

  iniciaTurma() {
    this.periodos = [];
    this.periodos.push({ label: 'Selecione...', value: null });
    this.periodos.push({ label: 'Matutino', value: 'MATUTINO' });
    this.periodos.push({ label: 'Vespertino', value: 'VESPERTINO' });
    this.periodos.push({ label: 'Noturno', value: 'NOTURNO' });
  }

  iniciaSerie() {
    this.series = [];
    this.series.push({ label: 'Selecione...', value: null });
    this.series.push({ label: '5º Seríe', value: '5-SERIE' });
    this.series.push({ label: '6º Seríe', value: '6-SERIE' });
    this.series.push({ label: '7º Seríe', value: '7-SERIE' });
    this.series.push({ label: '8º Seríe', value: '5-SERIE' });
    this.series.push({ label: '9º Seríe', value: '5-SERIE' });
    this.series.push({ label: '1º Ano', value: '1-ANO' });
    this.series.push({ label: '2º Ano', value: '2-ANO' });
    this.series.push({ label: '3º Ano', value: '3-ANO' });
  }

  carregarTurma(codigo: number) {
    this.turmaService.buscarPorCodigo(codigo)
      .then(turma => {
        this.turma = turma;
        this.atribuirValores(this.turma);
      }).catch(erro => this.errorHandle.handle(erro));
  }

  atribuirValores(turma: Turma) {
    this.turmaForm.setValue(turma);
  }

  salvar() {
    this.turmaService.salvar(this.turmaForm.value).then(() => {
      this.turma = new Turma();
      this.toasty.success(Mensagem.MENSAGEM_SALVO_SUCESSO);
      this.turmaForm.reset();
      setTimeout(() => {
        this.route.navigate(['/turmas']);
      });
    }).catch(erro => this.errorHandle.handle(erro));
  }

}
