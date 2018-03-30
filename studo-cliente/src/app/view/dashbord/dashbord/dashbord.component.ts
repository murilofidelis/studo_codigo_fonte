import { Component, OnInit } from '@angular/core';
import { DashbordService } from '../../../service/dashbord.service';

@Component({
  selector: 'app-dashbord',
  templateUrl: './dashbord.component.html',
  styleUrls: ['./dashbord.component.css']
})
export class DashbordComponent implements OnInit {

  dadosEscola: any;

  constructor(private dashBordService: DashbordService) { }

  ngOnInit() {
    this.montarDashbord();
  }

  montarDashbord() {
    this.dashBordService.buscarDadosDashbord().subscribe(dados => {
      this.dadosEscola = {
        labels: ['Professores', 'Alunos', 'Turmas', 'Disciplinas'],
        datasets: [
          {
            label: 'Dados da Escola',
            backgroundColor: '#42A5F5',
            data: [dados.numProfessores, dados.numAlunos, dados.numTurmas, dados.numDisciplinas]
          }
        ]
      };
    });
  }

}
