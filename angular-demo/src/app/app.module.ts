import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";  
import { FormsModule,ReactiveFormsModule} from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component'; 

@NgModule({
  declarations: [
    AppComponent 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,  
    ReactiveFormsModule,
    ModalModule.forRoot() 
  ],
  exports: [ModalModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
