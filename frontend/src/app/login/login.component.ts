import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../auth.service';
import { MapComponent } from '../map/map.component';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule, MapComponent],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup;
  loading = false;
  error: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      this.toastr.error('Please fill in all required fields correctly.', 'Error');
      return;
    }

    this.loading = true;
    const { username, password } = this.loginForm.value;
    console.log('Login form values:', { username, password });

    this.authService.login(username, password).subscribe({
      next: (response) => {
        this.loading = false;
        this.toastr.success('Login successful!', 'Success');
        console.log('Login successful:', response);
        this.router.navigateByUrl("/admin-dashboard"); // Adjust this based on your logic
      },
      error: (err) => {
        this.loading = false;
        this.error = 'login failed';
        this.toastr.error('login failed. please check your credentials and try again.', 'Error');
        console.error('Login error:', err);
      },
    });
  }

  createAccount() {
    if (this.loginForm.invalid) {
      this.toastr.error('Please fill in all required fields correctly.', 'Error');
      return;
    }

    this.loading = true;
    const { username, password } = this.loginForm.value;
    console.log("create ", { username, password });

    this.authService.register(username, password).subscribe({
      next: (response) => {
        this.loading = false;
        this.toastr.success('Registration successful!', 'Success');
        console.log('Registration successful:', response);
        this.router.navigateByUrl("/login");
      },
      error: (err) => {
        this.loading = false;
        this.error = 'Registration failed';
        this.toastr.error('Registration failed. Please check your credentials and try again.', 'Error');
        console.error('Registration error:', err);
      }
    });
  }
}
