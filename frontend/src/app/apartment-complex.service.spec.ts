import { TestBed } from '@angular/core/testing';

import { ApartmentComplexService } from './apartment-complex.service';

describe('ApartmentComplexService', () => {
  let service: ApartmentComplexService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApartmentComplexService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
