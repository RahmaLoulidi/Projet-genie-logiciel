import { TestBed } from '@angular/core/testing';

import { BesoinLivrableService } from './besoin-livrable.service';

describe('BesoinLivrableService', () => {
  let service: BesoinLivrableService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BesoinLivrableService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
