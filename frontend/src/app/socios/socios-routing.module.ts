import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SociosComponent } from './socios/socios.component';

const routes: Routes = [
  { path: '', component: SociosComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SociosRoutingModule { }
