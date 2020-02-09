import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { UserDTO } from 'src/dto/userdto';
import { HttpClient } from '@angular/common/http';
import { LoginDTO } from 'src/dto/logindto';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService extends AbstractService<UserDTO> {

  constructor(http: HttpClient) {
    super(http);
    this.type = 'user';
  }

  autheticate(loginDTO: LoginDTO) {
    return this.http.post<any>('http://localhost:' + this.port + '/authenticate', loginDTO );
  }

}
