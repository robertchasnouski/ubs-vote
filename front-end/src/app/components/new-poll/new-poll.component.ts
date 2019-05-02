import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-new-poll',
  templateUrl: './new-poll.component.html',
  styleUrls: ['./new-poll.component.css'],

})
export class NewPollComponent {
  @Output() createNew: EventEmitter<string> = new EventEmitter();
  form = new FormGroup({
    question: new FormControl('')})


  createPoll() {
    this.createNew.emit(this.form.get('question').value);
    this.form.setValue({question: ''});
  }
}
