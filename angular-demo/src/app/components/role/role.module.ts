import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule} from '@angular/forms';
import { RoleRoutingModule } from './role-routing.module';
import { RoleComponent } from '../../components/role/role.component'

@NgModule({
  imports: [
    CommonModule,
    RoleRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [RoleComponent]
})
export class RoleModule { }
