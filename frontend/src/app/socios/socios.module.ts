import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SociosRoutingModule } from './socios-routing.module';
import { SociosComponent } from './containers/socios/socios.component';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { SocioFormComponent } from './containers/socio-form/socio-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SociosListComponent } from './components/socios-list/socios-list.component';

@NgModule({
  declarations: [
    SociosComponent,
    SocioFormComponent,
    SociosListComponent
  ],
  imports: [
    CommonModule,
    SociosRoutingModule,
    AppMaterialModule,
    ReactiveFormsModule
  ]
})
export class SociosModule { }
