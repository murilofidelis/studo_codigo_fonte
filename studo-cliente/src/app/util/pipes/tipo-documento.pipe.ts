import { PipeTransform, Pipe } from '@angular/core';

const TIPO = {
    TIPO_A: 'Tipo A',
    TIPO_B: 'Tipo B'
};

@Pipe({
    name: 'tipoDocumento'
  })
export class TipoDocumentoPipe implements PipeTransform {

    transform(value: string) {
        return TIPO[value];
    }
}
