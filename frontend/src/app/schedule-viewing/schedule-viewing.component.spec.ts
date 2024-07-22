import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleViewingComponent } from './schedule-viewing.component';

describe('ScheduleViewingComponent', () => {
  let component: ScheduleViewingComponent;
  let fixture: ComponentFixture<ScheduleViewingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ScheduleViewingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleViewingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
