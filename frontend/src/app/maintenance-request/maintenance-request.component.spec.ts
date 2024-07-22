import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaintenenceRequestComponent } from './maintenence-request.component';

describe('MaintenenceRequestComponent', () => {
  let component: MaintenenceRequestComponent;
  let fixture: ComponentFixture<MaintenenceRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MaintenenceRequestComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MaintenenceRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
