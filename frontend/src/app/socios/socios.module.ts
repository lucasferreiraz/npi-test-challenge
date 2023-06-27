import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SociosRoutingModule } from './socios-routing.module';
import { SociosComponent } from './containers/socios/socios.component';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { SocioFormComponent } from './containers/socio-form/socio-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SociosListComponent } from './components/socios-list/socios-list.component';
import { CurrencyBrPipe } from '../pipes/currency-br.pipe';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    SociosComponent,
    SocioFormComponent,
    SociosListComponent,
    CurrencyBrPipe
  ],
  imports: [
    CommonModule,
    SociosRoutingModule,
    AppMaterialModule,
    ReactiveFormsModule,
    SharedModule
  ]
})
export class SociosModule { }
