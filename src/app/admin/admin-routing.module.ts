import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import { AuthGuard } from '../../guard/auth/auth.guard';

const routes: Routes = [
  {
    path: 'admin-dashboard', component: AdminLayoutComponent, canActivate: [AuthGuard], children: [
      { path: '', component: AdminDashboardComponent },
      { path: 'users', component: UsersComponent },
      { path: 'work-in-progress', component: WorkInProgressComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
