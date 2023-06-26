import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DependentesService {

  private readonly API_URL = 'http://localhost:8080/'

  constructor(private httpClient: HttpClient) {
	}

  delete(id:number){
		return this.httpClient.delete(this.API_URL + 'dependente/' + id)
	}
}
