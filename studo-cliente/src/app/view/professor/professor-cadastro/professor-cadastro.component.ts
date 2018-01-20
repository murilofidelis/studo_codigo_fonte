import { Component, OnInit } from '@angular/core';

import { SelectItem } from 'primeng/components/common/api';

@Component({
  selector: 'app-professor-cadastro',
  templateUrl: './professor-cadastro.component.html',
  styleUrls: ['./professor-cadastro.component.css']
})
export class ProfessorCadastroComponent implements OnInit {

  sexo: SelectItem[];
  status = [
    { label: 'Ativo', value: true },
    { label: 'Inativo', value: false }
  ];

  constructor() { }

  ngOnInit() {
    this.iniciarSexo();
  }

  iniciarSexo() {
    this.sexo = [];
    this.sexo.push({ label: 'Selecione...', value: null });
    this.sexo.push({ label: 'Masculino', value: 'MASCULINO' });
    this.sexo.push({ label: 'Feminino', value: 'FEMININO' });
  }

}
