import { Service } from './service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import {UserDTO} from 'src/dto/userdto';

export abstract class AbstractService<DTO> implements Service<DTO> {

    type: string;
    port = '8080';

    constructor(protected http: HttpClient) {
    }

    user = JSON.parse(localStorage.getItem('autoken')) as UserDTO;

    getAll(): Observable<DTO[]> {
        return this.http.get<DTO[]>('http://localhost:' + this.port + '/' + this.type + '/findAll', {
          headers: {
              Authorization : 'Bearer ' + localStorage.getItem('jwt_token')
            }
      });
    }

    read(id: number): Observable<DTO> {
        return this.http.get<DTO>('http://localhost:' + this.port + '/' + this.type + '/read/' + id, {
          headers: {
              Authorization : 'Bearer ' + localStorage.getItem('jwt_token')
            }
      });
    }

    delete(id: number): Observable<any> {
        return this.http.delete('http://localhost:' + this.port + '/' + this.type + '/delete/' + id, {
          headers: {
              Authorization : 'Bearer ' + localStorage.getItem('jwt_token')
            }
      });
    }

    insert(dto: DTO): Observable<any> {
        return this.http.post('http://localhost:' + this.port + '/' + this.type + '/insert', dto, {
          headers: {
              Authorization : 'Bearer ' + localStorage.getItem('jwt_token')
            }
      });
    }

    update(dto: DTO): Observable<DTO> {
        return this.http.put<DTO>('http://localhost:' + this.port + '/' + this.type + '/update', dto, {
          headers: {
              Authorization : 'Bearer ' + localStorage.getItem('jwt_token')
            }
      });
    }
}
