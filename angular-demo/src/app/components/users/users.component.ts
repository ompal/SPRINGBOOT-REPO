import {
  Component,
  OnChanges,
  OnInit,
  DoCheck,
  AfterContentInit,
  AfterContentChecked,
  AfterViewInit,
  AfterViewChecked,
  OnDestroy
} from '@angular/core';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements 
OnChanges,
OnInit,
DoCheck,
AfterContentInit,
AfterContentChecked,
AfterViewInit,
AfterViewChecked,
OnDestroy
{
  data:number=100;
    constructor() {
        console.log("constructor - data is:" ,this.data);
    }
    ngOnChanges() {
        console.log("ngOnChanges - data is:", this.data);
    }
    ngOnInit() {
        console.log("ngOnInit  - data is", this.data);
    }
    ngDoCheck() {
        console.log("ngDoCheck")
    }
    ngAfterContentInit() {
        console.log("ngAfterContentInit");
    }
    ngAfterContentChecked() {
        console.log("ngAfterContentChecked");
    }
    ngAfterViewInit() {
        console.log("ngAfterViewInit");
    }
    ngAfterViewChecked() {
        console.log("ngAfterViewChecked");
    }
    ngOnDestroy() {
        console.log("ngOnDestroy");
    }
    fnAddNumber():void{
        this.data+=100;
    }
    deleteNumber():void{
        this.data -=10;
    }
}
