import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class ContactUsComponent {
  onSubmit() {
    alert('Form Submitted Successfully');
  }
}
