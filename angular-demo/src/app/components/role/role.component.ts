import { Component, OnInit, TemplateRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators} from "@angular/forms";
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Role } from '../../models/role'; 
import { GenericService } from '../../services/generic.service';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {

  public roleList: Role[];
  public modalRef: BsModalRef; 
  public roleForm: FormGroup; 
  public isUpdated: boolean = false; 
  private url: string; 
  public successMessage: string; 
  public errorMessage: string; 
  
  constructor(
    private formBuilder: FormBuilder,
    private modalService: BsModalService, 
    private genericService: GenericService) 
    { 
      this.url = "role";
    }

  public openModal(roleTemplate: TemplateRef<any>) { 
    this.isUpdated = false;
    this.formReset();
    this.modalRef = this.modalService.show(roleTemplate);
  } 

  ngOnInit() { 
    this.roleForm = this.formBuilder
    .group({
      id: [],
      role: ['', Validators.required] 
    });  
    this.successMessage = "";
    this.errorMessage = "";
    this.getAllRoles(); 
  } 

  getAllRoles(){
    this.genericService.getAll(this.url)
    .subscribe( (data: Role[]) => { 
      this.roleList = data;
      console.log("rolesData:",data); 
    },(errorResponse) => {
      this.errorMessage = errorResponse.error;
      console.log("Roles not fetched!, Error: ",this.errorMessage);
    }); 
  }

  onSubmit() { 
    if(this.isUpdated){
      this.updateRole(this.roleForm.value); 
      this.modalRef.hide();           
    } else {
      this.createRole(this.roleForm.value); 
      this.formReset();
    }   
  }

  public createRole(role: Role) { 
    this.genericService.add(role,this.url).subscribe( 
      data => {
        this.successMessage = "Role Created successfully!";   
        console.log(this.successMessage) 
        this.getAllRoles();
      },(errorResponse) => {
        this.errorMessage = errorResponse.error;
        console.log("Role not created!, Error: ",this.errorMessage); 
      }); 
  }

  public updateRole(role: Role) { 
    this.genericService.update(role,this.url).subscribe( 
      data => {
        this.successMessage = "Role Updated successfully!";  
        console.log(this.successMessage)
        this.getAllRoles();
      },(errorResponse) => {
        this.errorMessage = errorResponse.error;
        console.log("Role not updated!, Error: ",this.errorMessage);
      });
  }

  public editRole(roleTemplate: TemplateRef<any>,role: Role) { 
    this.roleForm = this.formBuilder.group({
      id: [],
      role: ['', Validators.required] 
    }); 
    this.roleForm.setValue(role); 
    this.isUpdated = true;
    this.successMessage = "";
    this.modalRef = this.modalService.show(roleTemplate);
  }


  public deleteRole(id: number) {  
    this.genericService.delete(this.url+"/"+id).subscribe( 
      data => {
        console.log("Deleted successfully!") 
        this.successMessage = "Role Deleted successfully!";   
        this.getAllRoles();
      },(errorResponse) => {
        this.errorMessage = errorResponse.error;
        console.log("Role not deleted!, Error: ",this.errorMessage);
      }); 
  }

  formReset(){
    this.roleForm = this.formBuilder.group({  
      role: ''
    });
  }

  resetMessage(){
    setTimeout(() => {
      this.successMessage = "";
      this.errorMessage = ""; 
    }, 3000);
  }
}

