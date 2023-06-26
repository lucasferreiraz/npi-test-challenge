import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SociosComponent } from './containers/socios/socios.component';
import { SocioFormComponent } from './containers/socio-form/socio-form.component';
import { SocioResolver } from './guards/socio.resolver';

const routes: Routes = [
  { path: '', component: SociosComponent },
  { path: 'new', component: SocioFormComponent, resolve: { socio: SocioResolver } },
  { path: 'edit/:id', component: SocioFormComponent, resolve: { socio: SocioResolver } }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SociosRoutingModule { }
