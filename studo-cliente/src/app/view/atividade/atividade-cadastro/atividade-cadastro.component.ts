import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { SelectItem } from 'primeng/components/common/api';

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

  constructor(
    private formBuilder: FormBuilder,
    private atividadeService: AtividadeService,
    private disciplinaService: DisciplinaService
  ) { }

  ngOnInit() {
    this.atividadeForm = this.formBuilder.group({
      'codigo': [null],
      'titulo': [null, Validators.required],
      'descricao': [null, Validators.required],
      'classificacao': [null],
      'disciplina': [null, Validators.required]
    });

    this.carregarDisicplinas();
  }

  carregarDisicplinas() {
    this.disciplinas = [];
    this.disciplinas.push({ label: 'Selecione...', value: null });
    this.disciplinaService.buscaTodas().then(disciplinas => {
      disciplinas.forEach(disciplina => {
        this.disciplinas.push({ label: disciplina.descricao, value: disciplina.codigo });
      });
    });
  }

}
