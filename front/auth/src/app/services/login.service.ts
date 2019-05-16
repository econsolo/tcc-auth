import {Inject, Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {API_URL} from '../../assets/constants';
import {map} from 'rxjs/internal/operators';

@Injectable()
export class LoginService {

  private baseUrl = `${API_URL}/auth/`;

  constructor(@Inject(Http) private http: Http) {
  }

  public authenticate(user) {
    return this.http.post(this.baseUrl, user).pipe(map(res => res.json()));
  }

}
