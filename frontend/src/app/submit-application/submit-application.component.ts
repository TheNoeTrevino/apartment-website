import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ContactUsComponent } from '../contact-us/contact-us.component';

@Component({
  selector: 'app-submit-application',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ContactUsComponent
  ],
  templateUrl: './submit-application.component.html',
  styleUrls: ['./submit-application.component.scss']
})
export class SubmitApplicationComponent {
  selectedFile: File | null = null;

  constructor(private toastr: ToastrService) { }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      console.log('File selected:', this.selectedFile.name);
    }
  }

  onSubmit() {
    if (this.selectedFile) {
      console.log('Submitting file:', this.selectedFile.name);
      this.toastr.success('File submitted successfully!', 'Success');
      // You can implement your file processing logic here
    } else {
      this.toastr.error('No file selected', 'Error');
    }
  }
}
