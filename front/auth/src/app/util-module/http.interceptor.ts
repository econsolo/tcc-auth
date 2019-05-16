import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { UtilService } from './util.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError, Observable } from 'rxjs';

@Injectable()
export class Interceptor implements HttpInterceptor {

    constructor(private router: Router,
        private utilService: UtilService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // coloca o identificador (token) no cabeçalho da requisição
        request = request.clone({
            setHeaders: {
                Authorization: this.utilService.getToken()
            }
        });
        return next.handle(request).pipe(
            catchError(err => {

                const msg = 'Ocorreu um erro não esperado.';
                const erro = err.error;

                switch (err.status) {
                    case 401: // não autorizado
                        this.utilService.errorMsg(erro.errors);
                        this.router.navigate(['login']);
                        break;
                    default:
                        this.utilService.errorMsg(msg);
                        break;
                }

                return throwError(err);
            })
        );
    }

}

