import { Turma } from './turma.model';
import { Email } from './email.model';
import { Matricula } from './matricula.model';

export class Aluno {
  codigo: number;
  nome: string;
  cpf: string;
  sexo: string;
  dataNascimento: Date;
  email: Email = new Email();
}
