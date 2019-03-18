import { Inject, Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { FormGroup } from '@angular/forms';
import { LOCALSTORAGE_TOKEN_KEY } from '../../assets/constants';
import { MatSnackBar } from '@angular/material';


@Injectable()
export class UtilService {

  constructor(@Inject(Http) private http: Http,
              @Inject(MatSnackBar) private snackBar: MatSnackBar) {

  }

  public showErrors(form: FormGroup): void {
    if (!form) {
      throw new Error('[showErrors] O FormGroup não deve estar nulo!');
    }
    Object.keys(form.controls).forEach(key => {
      form.get(key).markAsTouched();
    });
  }

  public errorMsg(msg: string = 'Ocorreu um erro desconhecido!'): void {
    this.snackBar.open(msg);
  }

  public successMsg(msg: string): void {
    this.validateMsg(msg);
    this.snackBar.open(msg);
  }

  public successMsgInternal(msg: string): void {
    this.validateMsg(msg);
    this.snackBar.open(msg);
  }

  private validateMsg(mensagem: string): void {
    if (!mensagem) {
      throw new Error('É obrigatório definir uma mensagem');
    }
  }
  public saveCookies(data: any): void {
    localStorage.setItem(LOCALSTORAGE_TOKEN_KEY, JSON.stringify(data));
  }

  public clearCookies(): void {
    localStorage.clear();
  }

  public isLogged(): any {
    const item = localStorage.getItem(LOCALSTORAGE_TOKEN_KEY);
    return !!item;
  }

  public getAuth(): any {
    if (this.isLogged()) {
      return JSON.parse(localStorage.getItem(LOCALSTORAGE_TOKEN_KEY));
    }
    return null;
  }

  public getName(): string {
    if (this.isLogged()) {
      const obj = JSON.parse(localStorage.getItem(LOCALSTORAGE_TOKEN_KEY));
      return obj.user_name;
    }
    return null;
  }


}
