import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserPage} from "../model/userPage";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private http: HttpClient) { }

  public findAll(page:number){
    return this.http.get<UserPage>(`api/v1/user?page=${page}`);
  }

  public findById(id:number) {
    return this.http.get<User>(`api/v1/user/${id}`)

  }
  public save(user: User){
    return this.http.put(`api/v1/user`, user);
  }
  public deleteById(id:number){
    return this.http.delete(`api/v1/user/${id}`)
  }
}
