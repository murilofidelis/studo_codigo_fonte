import { Aluno } from './aluno.model';
import { Turma } from './turma.model';

export class Matricula {
  codigo: number;
  aluno: Aluno;
  turma: Turma;
  dataCadastro: Date;
  turmaAtual: boolean;

  constructor() {
    this.aluno = new Aluno();
    this.turma = new Turma();
  }
}
