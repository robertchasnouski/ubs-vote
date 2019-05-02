import { Injectable } from '@angular/core';
import {User} from "../model/model.user";
import {Http} from "@angular/http";
import {AppComponent} from "../app.component";
import {API_URL} from "../config";

@Injectable()
export class AccountService {
  constructor(public http: Http) { }

  createAccount(user:User){
    return this.http.post(API_URL+'/account/register',user)
      .map(resp=>resp.json());
  }
}
