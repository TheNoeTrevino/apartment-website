import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms'
import { FormBuilder, Validators } from '@angular/forms'
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-forget-password',
  standalone: true,
  imports: [],
  templateUrl: './forget-password.component.html',
  styleUrl: './forget-password.component.scss'
})
export class ForgetPasswordComponent implements OnInit {
  forgetPasswordForm!: FormGroup;

  constructor(
    private _authService: AuthService,
    private _fb: FormBuilder,
    private _toastr: ToastrService
  ) {

  }

  ngOnInit(): void {
    this.forgetPasswordForm = this._fb.group({
      email: ['', [Validators.required, Validators.email]]
    })
  }


  submit(formValue: any) {
    let postData = {
      email: formValue.email
    }

    this._authService.forgetPassword(postData).subscribe({
      next: (res: any) => {
        if (res.status === 200) {
          //Write your logic after response is received
          console.log('ok')
        }
      },
      error: (err: any) => {
        this._toastr.error(err.error.message, 'Error');
      },
      complete: () => {
        this._toastr.success('Email sent successfully', 'Success')
      }
    })
  }

}
