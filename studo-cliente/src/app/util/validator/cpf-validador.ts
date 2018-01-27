import { AbstractControl } from '@angular/forms';


export class ValidadorCPF {
  static validate(c: AbstractControl): { [p: string]: any } {
    const cpf = c.value ? c.value.replace(/[^\d]+/g, '') : '';

    // tslint:disable-next-line:curly
    if (cpf === '') return { valid: false };

    // tslint:disable-next-line:curly
    if (cpf.length !== 11 ||
      cpf === '00000000000' ||
      cpf === '11111111111' ||
      cpf === '22222222222' ||
      cpf === '33333333333' ||
      cpf === '44444444444' ||
      cpf === '55555555555' ||
      cpf === '66666666666' ||
      cpf === '77777777777' ||
      cpf === '88888888888' ||
      cpf === '99999999999')
      return { valid: false };

    let add = 0;
    // tslint:disable-next-line:curly
    for (let i = 0; i < 9; i++)
      // tslint:disable-next-line:radix
      add += parseInt(cpf.charAt(i)) * (10 - i);
    let rev = 11 - (add % 11);
    // tslint:disable-next-line:curly
    if (rev === 10 || rev === 11)
      rev = 0;
    // tslint:disable-next-line:curly
    if (rev !== parseInt(cpf.charAt(9)))
      return { valid: false };

    add = 0;
    // tslint:disable-next-line:curly
    for (let i = 0; i < 10; i++)
      // tslint:disable-next-line:radix
      add += parseInt(cpf.charAt(i)) * (11 - i);
    rev = 11 - (add % 11);
    // tslint:disable-next-line:curly
    if (rev === 10 || rev === 11)
      rev = 0;

    if (rev !== parseInt(cpf.charAt(10)))
      return { valid: false };
    return null;
  }
}

