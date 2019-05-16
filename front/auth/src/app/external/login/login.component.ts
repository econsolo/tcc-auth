import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UtilService } from '../../util-module/util.service';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.style.css']
})

export class LoginComponent implements OnInit {

  public form: FormGroup;
  public hide = true;

  constructor(@Inject(Router) public router: Router,
              @Inject(FormBuilder) private formBuilder: FormBuilder,
              @Inject(UtilService) private utilService: UtilService,
              @Inject(LoginService) private loginService: LoginService) {

    this.form = formBuilder.group({
      email: ['', [
        Validators.required,
        Validators.maxLength(128),
        Validators.email
      ]],
      password: ['', [
        Validators.required,
        Validators.maxLength(128),
        Validators.minLength(3)
      ]]
    });

  }

  ngOnInit() {
    this.utilService.clearCookies();
  }

  public login(user: any): void {
    if (this.form.invalid) {
      this.utilService.showErrors(this.form);
      return;
    }

    this.loginService.authenticate(user).subscribe(data => {
      if (!data) {
        this.utilService.errorMsg('Usuário e/ou senha incorreto(s)');
        return;
      }

      this.utilService.saveCookies(data);

      this.utilService.successMsg('Login efetuado!');
    });

  }

  public goTo(route: string): void {
    this.router.navigate([route]);
  }


}
