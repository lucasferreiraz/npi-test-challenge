import { SociosService } from '../../services/socios.service';
import { Component } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { Socio } from '../../model/socio';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-socios',
  templateUrl: './socios.component.html',
  styleUrls: ['./socios.component.scss']
})
export class SociosComponent {

  socios: Socio[] = []
  totalElements: number = 0
  displayedColumns = ["id", "nome", "renda", "ativo", "actions"]

  constructor(
    private sociosService: SociosService,
    private router: Router,
    private route: ActivatedRoute
  ) {
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
    this.router.navigate(['new'], { relativeTo: this.route })
  }

  nextPage(event: PageEvent) {
    const request = {};
    request['page'] = event.pageIndex.toString();
    request['size'] = event.pageSize.toString();
    this.getProducts(request);
  }

}
