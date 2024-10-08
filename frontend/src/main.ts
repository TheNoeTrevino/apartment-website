import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { appConfig } from './app/app.config';
import { provideHttpClient } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideToastr } from 'ngx-toastr';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(), // Add this to provide HttpClientModule
    provideAnimations(), // required animations providers
    provideToastr(), // Toastr providers
    ...appConfig.providers, provideAnimationsAsync(), // Include the existing appConfig providers
  ],
}).catch(err => console.error(err));

