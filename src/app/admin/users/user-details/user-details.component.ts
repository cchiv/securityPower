import { Component, OnInit, Input } from '@angular/core';
import { AbstractCrudComponent } from 'src/app/utils/abstractcomponent';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';


@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['../users.component.css']
})
export class UserDetailsComponent extends AbstractCrudComponent<UserDTO> implements OnInit {


  @Input() dto: UserDTO;

  constructor(service: UserService) {
    super(service);
  }

  ngOnInit() {
  }

}
