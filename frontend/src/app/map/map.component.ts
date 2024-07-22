import { Component, ViewChild } from '@angular/core';
import { GoogleMap } from '@angular/google-maps';

interface MarkerProperties {
  position: {
    lat: number;
    lng: number;
  },
  title: string;
};

@Component({
  selector: 'app-map',
  standalone: true,
  imports: [GoogleMap],
  templateUrl: './map.component.html',
  styleUrl: './map.component.scss'
})
export class MapComponent {
  mapOptions: google.maps.MapOptions = {
    center: { lat: 26.1992329, lng: -98.18840005 },
    zoom: 12,
    mapTypeControl: false,
  };

  markers: MarkerProperties[] = [
    { position: { lat: 26.1647338, lng: -98.2066864 }, title: 'Jackson South Apartments' },
    { position: { lat: 26.2337320, lng: -98.1701137 }, title: 'Santa Lucia Apartments' },
  ];

  handleMapInitialized(map: google.maps.Map) {
    this.markers.forEach((marker: MarkerProperties) => {
      const mapMarker = new google.maps.Marker({
        position: marker.position,
        map,
        title: marker.title
      });

      const infoWindow = new google.maps.InfoWindow({
        content: `<div class="info-window">${marker.title}</div>`
      });

      mapMarker.addListener('click', () => {
        infoWindow.open(map, mapMarker);
      });
    });
  }
}
