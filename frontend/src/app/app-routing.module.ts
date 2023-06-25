import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'socios', pathMatch: 'full'},
  {
    path: 'socios',
    loadChildren: () => import('./socios/socios.module').then(m => m.SociosModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
