import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit{
  code:number= 403;
  message: string="Vous n'êtes pas connectés";



  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

}
