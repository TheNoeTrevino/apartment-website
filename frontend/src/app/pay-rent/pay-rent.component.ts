import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { PayRentService } from '../pay-rent.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-pay-rent',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './pay-rent.component.html',
  styleUrls: ['./pay-rent.component.scss']
})
export class PayRentComponent {
  rentForm: FormGroup;
  rentAmount: number = 0;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private payRentService: PayRentService,
    private toastr: ToastrService
  ) {
    this.rentForm = this.fb.group({
      paymentAmount: ['']
    });
    this.getRentDetails();
  }

  getRentDetails() {
    this.loading = true;
    this.payRentService.getRentDetails().subscribe(
      (data) => {
        this.rentAmount = data.rentAmount;
        this.loading = false;
      },
      (error) => {
        this.loading = false;
        this.toastr.error('Failed to fetch rent details.', 'Error');
        console.error(error);
      }
    );
  }

  onSubmit() {
    if (this.rentForm.invalid) {
      this.toastr.error('Please enter a valid amount.', 'Error');
      return;
    }

    this.loading = true;
    const paymentAmount = this.rentForm.get('paymentAmount')?.value;

    this.payRentService.payRent(paymentAmount).subscribe(
      (response) => {
        this.loading = false;
        this.toastr.success('Rent payment successful!', 'Success');
        this.getRentDetails();
      },
      (error) => {
        this.loading = false;
        this.toastr.error('Failed to process payment.', 'Error');
        console.error(error);
      }
    );
  }
}
