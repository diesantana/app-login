import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { matchPasswordValidator } from '../../../validators/match-password-validatior';
import { ToastrService } from 'ngx-toastr';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  registerForm: FormGroup = new FormGroup({});

  constructor(
    private formBuilder: FormBuilder,
    private toastService: ToastrService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.initializeForm(); // Inicializa o formulário ao iniciar o componente
  }

  initializeForm(): void {
    // Cria o FormGroup com os campos e validadores necessários
    this.registerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6)]],
      acceptTerms: [false, [Validators.requiredTrue]], // Adiciona o campo de checkbox com validação obrigatória
    }, { validators: matchPasswordValidator }); // Aplica o validador customizado
  }

  submitForm(): void {
    if (this.registerForm.valid) {
      const name = this.registerForm.value.name;
      const email = this.registerForm.value.email;
      const password = this.registerForm.value.password;
      // lógica de cadastro aqui
      this.loginService.signup(name, email, password).subscribe({
        next: response => {
          this.toastService.success("Cadastro feito com sucesso!");
          // **************************************************************************************
          // Lógica para redireciona para a página home
        },
        error: err => {
          this.toastService.error("Erro insesperado, tente novamente mais tarde.");
          console.log(err);
        }
      });



      console.log(this.registerForm.value);
      this.registerForm.reset();
    }
  }
}
