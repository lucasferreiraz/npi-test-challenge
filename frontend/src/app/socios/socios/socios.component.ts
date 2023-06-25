import { SociosService } from './../services/socios.service';
import { Component } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { Socio } from '../model/socio';

@Component({
  selector: 'app-socios',
  templateUrl: './socios.component.html',
  styleUrls: ['./socios.component.scss']
})
export class SociosComponent {

  socios: Socio[] = []
  totalElements: number = 0
  displayedColumns = ["id", "nome", "renda", "ativo"]

  constructor(private sociosService: SociosService) {
    this.getProducts({ page: "0", size: "5" });
  }

  private getProducts(request: any) {
    this.sociosService.getAll(request)
      .subscribe(data => {
        this.socios = data['content'];
        this.totalElements = data['totalElements'];
      });
  }

  onAdd() {
    
  }

  nextPage(event: PageEvent) {
    const request = {};
    request['page'] = event.pageIndex.toString();
    request['size'] = event.pageSize.toString();
    this.getProducts(request);
  }

}
