import { Endereco } from './endereco.model';
import { Email } from './email.model';
import { Usuario } from './usuario.model';

export class Professor {
  codigo: number;
  nome: string;
  cpf: string;
  sexo: string;
  email: Email;
  endereco: Endereco;
  usuario: Usuario;

  constructor() {
    this.email = new Email();
    this.endereco = new Endereco();
    this.usuario = new Usuario();
  }
}
