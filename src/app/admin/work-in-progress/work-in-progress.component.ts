import { Component, OnInit } from '@angular/core';

/**
 * Questa component serve solo a mostrare un'immagine buffa quando si vuole andare ad una component
 * che ancora non è stata creata.
 * 
 * @author Vittorio Valent
 */
@Component({
  selector: 'app-work-in-progress',
  templateUrl: './work-in-progress.component.html',
  styleUrls: ['./work-in-progress.component.css']
})
export class WorkInProgressComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
