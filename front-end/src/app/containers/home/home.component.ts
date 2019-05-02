import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {User} from "../../model/model.user";
import {Router} from "@angular/router";
import {Poll, PollForm} from "../../model/model.poll";
import {PollsService} from "../../services/polls.service";
import {serializePollsToPollsForm} from "../../serializers";
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  currentUser: User;
  polls: Poll[];
  pollsForm: PollForm[];

  constructor(public authService: AuthService,
              public router: Router,
              public pollsService: PollsService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.fetchPolls();

  }

  fetchPolls() {
    this.pollsService.fetchPolls().subscribe(allPolls => {
      console.log("FETCHED POLLS:",allPolls);
      this.pollsForm = serializePollsToPollsForm(allPolls);

    });
  }

  logOut() {
    this.authService.logOut()
      .subscribe(
        data => {
          this.router.navigate(['/login']);
        },
        error => {
        });
  }
}
