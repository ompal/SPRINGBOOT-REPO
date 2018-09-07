import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse } from '@angular/common/http'; 
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs'; 


@Injectable({
  providedIn: 'root'
})
export class GenericService {
 
  actionUrl: string;
  constructor(private http: HttpClient) { 
    this.actionUrl = environment.baseUrl;
   }

   public getAll<T>(postFixUrl: string): Observable<T[]> { 
    return this.http.get<T[]>(this.actionUrl+postFixUrl)
    }

  public getById<T>(postFixUrl: string): Observable<T> {
    return this.http.get<T>(this.actionUrl+postFixUrl);
    }

  public add<T>(entity: T,postFixUrl: string): Observable<T> { 
    return this.http.post<T>(this.actionUrl+postFixUrl, entity);
  }

  public update<T>(entity: T,postFixUrl: string): Observable<T> {
      return this.http.put<T>(this.actionUrl+postFixUrl,entity);
  }

  public delete<T>(postFixUrl: string): Observable<string> {
      return this.http.delete<string>(this.actionUrl+postFixUrl);
  }
}
