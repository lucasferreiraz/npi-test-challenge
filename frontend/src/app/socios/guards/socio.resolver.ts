import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { Socio } from '../model/socio';
import { SociosService } from '../services/socios.service';

@Injectable({
  providedIn: 'root'
})
export class SocioResolver implements Resolve<Socio> {

  constructor(private service: SociosService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Socio> {
    if(route.params && route.params['id']) {
      return this.service.findById(route.params['id'])
    }
    return of({id: '', nome: '', renda: '', ativo: ''});
  }
}
