import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-atividade-cadastro',
  templateUrl: './atividade-cadastro.component.html',
  styleUrls: ['./atividade-cadastro.component.css']
})
export class AtividadeCadastroComponent implements OnInit {

  disciplinas = [];

  constructor() { }

  ngOnInit() {
    this.carregarDisicplinas();
  }

  carregarDisicplinas() {
    this.disciplinas.push({ label: 'Selecione...', value: null });
  }
}
