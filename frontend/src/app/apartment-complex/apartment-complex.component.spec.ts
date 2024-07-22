import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApartmentComplexComponent } from './apartment-complex.component';

describe('ApartmentComplexComponent', () => {
  let component: ApartmentComplexComponent;
  let fixture: ComponentFixture<ApartmentComplexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApartmentComplexComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApartmentComplexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
