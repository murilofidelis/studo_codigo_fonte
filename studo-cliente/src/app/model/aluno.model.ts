import { Turma } from './turma.model';
import { Email } from './email.model';
import { Matricula } from './matricula.model';

export class Aluno {
  codigo: number;
  matricula: number;
  nome: string;
  sexo: string;
  dataNascimento: Date;
  email: Email;
  matriculas: Matricula[];

  constructor() {
    this.email = new Email();
    this.matriculas = [];
  }
}
