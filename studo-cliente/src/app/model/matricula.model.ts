import { Aluno } from './aluno.model';
import { Turma } from './turma.model';

export class Matricula {
  codigo: number;
  dataMatricula: Date;
  turmaAtual: boolean;
  aluno: Aluno = new Aluno();
  turma: Turma = new Turma();
}
