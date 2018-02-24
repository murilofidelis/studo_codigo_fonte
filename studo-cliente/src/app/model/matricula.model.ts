import { Aluno } from './aluno.model';
import { Turma } from './turma.model';

export class Matricula {
  codigo: number;
  aluno: Aluno;
  turma: Turma;
  dataMatricula: Date;
  turmaAtual: boolean;

  constructor() {
    this.turma = new Turma();
    this.aluno = new Aluno();
  }
}
