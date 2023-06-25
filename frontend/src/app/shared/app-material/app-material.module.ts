import { NgModule } from '@angular/core';

import { MatCardModule } from '@angular/material/card';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';



@NgModule({
  exports: [
    MatCardModule,
    MatPaginatorModule,
    MatTableModule,
    MatToolbarModule,
    MatIconModule
  ]
})
export class AppMaterialModule { }
