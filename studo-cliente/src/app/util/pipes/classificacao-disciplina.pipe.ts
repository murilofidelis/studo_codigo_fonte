import { Pipe, PipeTransform } from '@angular/core';

const CLASSIFICACAO = {
  QUINTA_SERIE: 'Quinta Série',
  SEXTA_SERIE: 'Sexta Série',
  SETIMA_SERIE: 'Setima série',
  OITAVA_SERIE: 'Oitava Seríe',
  NONA_SERIE: 'Nona Seríe',
  PRIMEIRO_ANO: 'Primeiro Ano',
  SEGUNDO_ANO: 'Segundo Ano',
  TERCEIRO_ANO: 'Terceiro Ano'
};

@Pipe({
  name: 'classificacaoDisciplina'
})
export class ClassificacaoDisciplinaPipe implements PipeTransform {
  transform(classificacao: string) {
    return CLASSIFICACAO[classificacao];
  }

}
