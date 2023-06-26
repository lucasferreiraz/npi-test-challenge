import { SociosService } from '../../services/socios.service';
import { Component } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { Socio } from '../../model/socio';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

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
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.getProducts({ page: "0", size: "10" });
  }

  private getProducts(request: any) {
    this.sociosService.getAll(request)
      .subscribe(data => {
        this.socios = data['content'];
        this.totalElements = data['totalElements'];
      });
  }

  refresh() {
    this.getProducts({ page: "0", size: "5" });
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route })
  }

  onEdit(socio: Socio) {
    this.router.navigate(['edit', socio.id], { relativeTo: this.route })
  }

  onRemove(socio: Socio) {
    this.refresh()
    this.sociosService.delete(socio.id as number).subscribe(
      () => {
        this.snackBar.open("Sócio deletado com sucesso.", '', {
          duration: 4000,
          verticalPosition: 'top',
          horizontalPosition: 'center'
        })
      }
    )
  }

  nextPage(event: PageEvent) {
    const request = {};
    request['page'] = event.pageIndex.toString();
    request['size'] = event.pageSize.toString();
    this.getProducts(request);
  }

}
