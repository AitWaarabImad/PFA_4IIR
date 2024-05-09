import { TestBed } from '@angular/core/testing';

import { SallereunionService } from './sallereunion.service';

describe('SallereunionService', () => {
  let service: SallereunionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SallereunionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
