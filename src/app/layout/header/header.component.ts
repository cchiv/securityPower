import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';

/**
 * Questa component è statica eccetto per il display dell'utente loggato (viene estratto dalla session
 * nel motodo ngOnInit() ). HeaderComponent è uno degli elementi che viene caricato nel Layout.
 * 
 * @author Vittorio Valent
 * 
 * @see AdminLayout
 */
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: UserDTO = new UserDTO;

  constructor() { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

}
