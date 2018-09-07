import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [  
    {
      path: 'roles',
      loadChildren: './components/role/role.module#RoleModule'
    },
    {
      path: 'index',
      loadChildren: './components/index/index.module#IndexModule'
    },
    {
      path: '',
      loadChildren: './components/index/index.module#IndexModule'
    },
    {
      path: 'users',
      loadChildren: './components/users/users.module#UsersModule'
    },
    {
      path: '',
      redirectTo: '',
      pathMatch: 'full'
    } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
