import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { SelectItem } from 'primeng/components/common/api';
import { ToastyService } from 'ng2-toasty';

import { Mensagem } from '../../../util/mensagens.util';

import { Atividade } from '../../../model/atividade.model';
import { DisciplinaService } from '../../../service/disciplina.service';
import { AtividadeService } from '../../../service/atividade.service';

@Component({
  selector: 'app-atividade-cadastro',
  templateUrl: './atividade-cadastro.component.html',
  styleUrls: ['./atividade-cadastro.component.css']
})
export class AtividadeCadastroComponent implements OnInit {

  disciplinas: SelectItem[];
  atividadeForm: FormGroup;
  atividade: Atividade;

  constructor(
    private formBuilder: FormBuilder,
    private toasty: ToastyService,
    private route: Router,
    private activatedRoute: ActivatedRoute,
    private atividadeService: AtividadeService,
    private disciplinaService: DisciplinaService
  ) { }

  ngOnInit() {
    this.atividadeForm = this.formBuilder.group({
      'codigo': [null],
      'titulo': [null, Validators.required],
      'descricao': [null, Validators.required],
      'classificacao': [null],
      'disciplina': this.formBuilder.group({
        'codigo': [null]
      }),
    });
    this.carregarDiscicplinas();
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

  salvar() {
    this.atividade = this.atividadeForm.value;
    this.atividadeService.salvar(this.atividade).then(res => {
      if (res) {
        this.atividadeForm.reset();
        this.toasty.success(Mensagem.MENSAGEM_SALVO_SUCESSO);
        setTimeout(() => {
          this.route.navigate(['/atividade']);
        }, 1000);
      }
    });
  }
}
