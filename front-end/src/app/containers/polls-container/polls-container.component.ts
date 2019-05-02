import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from "../../model/model.user";
import {PollsService} from "../../services/polls.service";
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {PollForm} from "../../model/model.poll";

@Component({
  selector: 'app-polls-container',
  templateUrl: './polls-container.component.html',
  styleUrls: ['./polls-container.component.css'],
})
export class PollsContainerComponent {
  _data: PollForm[] = [];
  form: FormGroup;

  @Output()
  fetchNew = new EventEmitter();

  @Input()
  set data (data: PollForm[]) {
    this._data = data;
    this.setForm(data);
  }
  get data() {
    return this._data;
  }

  constructor(private formBuilder: FormBuilder,
              private pollsService: PollsService) {
    this.form = new FormGroup({
      polls: this.formBuilder.array([]),
    })
  }


  setForm(data: PollForm[]) {
    const control = <FormArray>this.form.controls['polls'];

    for(let i = control.length-1; i >= 0; i--) {
      control.removeAt(i);
    }

    const controlArray = <FormArray>this.form.get('polls');
    if(data) {
      data.forEach(record => {
        const group = this.formBuilder.group({
          question: record.question,
          id: record.id,
          userChoice: record.userChoice,
          canVote: record.canVote,
          favorVoteCount: record.favorVoteCount,
          underdogVoteCount: record.underdogVoteCount
        })
        controlArray.push(group);
      });
    }
  }

  createPoll(question: string) {
    this.pollsService.createPoll(question).subscribe(param => this.fetchNew.emit());
    ;
  }

  sendVote(options) {
    this.pollsService.sendVote(options).subscribe(param => this.fetchNew.emit());
  }

}
