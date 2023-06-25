import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SociosService {

  private readonly API_URL = 'http://localhost:8080/'

  constructor(private httpClient: HttpClient) {
	}

  getAll(request): Observable<any> {
		const params = request;
		return this.httpClient.get(this.API_URL + 'socio', {params});
	}
}
