import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SociosRoutingModule } from './socios-routing.module';
import { SociosComponent } from './socios/socios.component';
import { AppMaterialModule } from '../shared/app-material/app-material.module';

@NgModule({
  declarations: [
    SociosComponent
  ],
  imports: [
    CommonModule,
    SociosRoutingModule,
    AppMaterialModule
  ]
})
export class SociosModule { }
