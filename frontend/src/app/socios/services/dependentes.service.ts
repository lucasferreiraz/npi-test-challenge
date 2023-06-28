import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DependentesService {

  private readonly API_URL = environment.apiUrl

  constructor(private httpClient: HttpClient) {
	}

  delete(id:number){
		return this.httpClient.delete(this.API_URL + 'dependente/' + id)
	}
}
