import { Disciplina } from './disciplina.model';
import { Professor } from './professor.model';

export class Atividade {

    codigo: number;
    dataCadastro: Date;
    titulo: string;
    descricao: string;
    classificacao: string;
    Professor: Professor = new Professor();
    disciplina: Disciplina = new Disciplina();
}
