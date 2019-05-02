import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PollForm} from "../../model/model.poll";


@Component({
  selector: ' app-poll',
  templateUrl: './poll.component.html',
  styleUrls: ['./poll.component.css'],
})
export class PollComponent implements OnInit{
  isRetryVoting: boolean;

  @Input()
  poll: PollForm;

  @Output()
  userVoted: EventEmitter<any> = new EventEmitter<any>();

  ngOnInit() {
    this.isRetryVoting = this.poll.canVote;
  }

  sendVote(vote: boolean) {
    this.userVoted.emit({id: this.poll.id, userChoice: vote})
  }


}
