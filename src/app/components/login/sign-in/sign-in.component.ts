import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {
  loginForm: FormGroup = new FormGroup({});

  constructor(
    private formBuilder: FormBuilder,
    private toastService: ToastrService,
    private loginService: LoginService
  ) {
  }

  ngOnInit(): void {
    this.initializeForm(); // Inicializa o formulário ao iniciar o componente
  }

  initializeForm(): void {
    // Cria o FormGroup com os campos e validadores necessários
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  submitForm(): void {
    if(this.loginForm.valid) {
      // Lógica de login aqui
      this.loginService.login(
        this.loginForm.value.email, this.loginForm.value.password
      ).subscribe({
        next: response => {
          this.toastService.success("Login feito com sucesso!");
          // **************************************************************************************
          // Lógica para redireciona para a página home
        },
        error: err => {
          this.toastService.error("Erro insesperado, tente novamente mais tarde.");
          console.log(err);
        }
      });

      this.loginForm.reset();
    }
  }

}
