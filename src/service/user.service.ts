import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { UserDTO } from 'src/dto/userdto';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractService<UserDTO> {

  constructor(http: HttpClient) {
    super(http);
    this.type = 'user';
  }

  findBy(username: string) {
    return this.http.get<any>('http://localhost:' + this.port + '/' + this.type + '/findByUsername/' + username , {
      headers: {
          Authorization : 'Bearer ' + localStorage.getItem('jwt_token')
        }
  });
  }


}
