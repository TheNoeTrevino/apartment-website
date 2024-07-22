import { TestBed } from '@angular/core/testing';

import { PayRentService } from './pay-rent.service';

describe('PayRentService', () => {
  let service: PayRentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PayRentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
