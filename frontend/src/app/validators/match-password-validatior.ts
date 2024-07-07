import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export const matchPasswordValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  // Obtém os campos de senha e confirmação de senha do AbstractControl
  const password = control.get('password');
  const confirmPassword = control.get('confirmPassword');

  // Verifica se os campos de senha e confirmação de senha não estão vazios e se os valores são iguais
  return password && confirmPassword && password.value === confirmPassword.value
    ? null // Retorna null se não houver erro (senhas iguais)
    : { passwordMismatch: true }; // Retorna um objeto de erro se as senhas não forem iguais
};
