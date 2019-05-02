import { Injectable } from '@angular/core';
import {User} from '../model/model.user';
import {Headers, Http, RequestOptions} from '@angular/http';
import {API_URL, CREATE_POLL_URL, GET_POLLS_URL, SEND_VOTE_URL} from '../config';

@Injectable()
export class PollsService {
  constructor(public http: Http) { }

  createPoll(question: string){
    let headers = new Headers();
    let token = localStorage.getItem("token");
    console.log(token);
    headers.append('Accept', 'application/json')
    headers.append("Authorization", "Basic " + token);

    let options = new RequestOptions();


    options.headers=headers;


    return this.http.post(CREATE_POLL_URL, {question : question },options)
      .map(resp=> {
        console.log(resp);
        return resp.json();
      });
  }

  sendVote(options: any) {
    let headers = new Headers();
    let token = localStorage.getItem("token");
    console.log(token);
    headers.append('Accept', 'application/json')
    headers.append("Authorization", "Basic " + token);

    let reqOptions = new RequestOptions();

    reqOptions.headers=headers;
    console.log("NEW WOTE:"+ options.userChoice+";ID:"+options.id);
    return this.http.post(SEND_VOTE_URL(options.id), {userChoice: options.userChoice}, reqOptions);
  }

  fetchPolls() {
    let headers = new Headers();
    let token = localStorage.getItem("token");
    console.log(token);
    headers.append('Accept', 'application/json')
    headers.append("Authorization", "Basic " + token);

    let options = new RequestOptions();
    options.headers=headers;

    return this.http.get(GET_POLLS_URL, options)
      .map(resp=> {
        console.log("BACKEND RESP:",resp);
        return resp.json();
      });
  }



}
