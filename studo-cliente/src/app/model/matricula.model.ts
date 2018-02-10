import { Aluno } from './aluno.model';
import { Turma } from './turma.model';

export class Matricula {
  codigo: number;
  turma: Turma;
  dataCadastro: Date;
  turmaAtual: boolean;

  constructor() {
    this.turma = new Turma();
  }
}
