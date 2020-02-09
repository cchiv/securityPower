import { Component, OnInit } from '@angular/core';
import { LoginDTO } from 'src/dto/logindto';
import { NgForm } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/dto/userdto';
import { AuthenticateService } from 'src/service/AuthenticateService';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDTO: LoginDTO;

  constructor(private service: UserService, private authService: AuthenticateService, private router: Router) { }

  ngOnInit() {
  }

  login(f: NgForm): void {
    this.loginDTO = new LoginDTO(f.value.username, f.value.password);

    this.authService.autheticate(this.loginDTO).subscribe((jwToken) => {
          localStorage.setItem('jwt_token', jwToken.token);

          this.service.findBy(this.loginDTO.username).subscribe((user: UserDTO) => {
            if (user != null) {
              localStorage.setItem('currentUser', JSON.stringify(user));

              switch (user.usertype.toString()) {
                case 'ADMIN': {
                  this.router.navigate(['/admin-dashboard']);
                  break;
                }
                case 'USER': {
                  this.router.navigate(['/admin-dashboard']);
                  break;
                }
                default:
                  this.router.navigate(['/login']);
              }
            }
          });

        });
    }
}

