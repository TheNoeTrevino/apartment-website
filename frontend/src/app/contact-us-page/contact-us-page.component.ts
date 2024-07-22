import { Component } from '@angular/core';
import { ContactUsComponent } from '../contact-us/contact-us.component';
import { MapComponent } from '../map/map.component';

@Component({
  selector: 'app-contact-us-page',
  standalone: true,
  imports: [ContactUsComponent, MapComponent],
  templateUrl: './contact-us-page.component.html',
  styleUrl: './contact-us-page.component.scss'
})
export class ContactUsPageComponent {

}
