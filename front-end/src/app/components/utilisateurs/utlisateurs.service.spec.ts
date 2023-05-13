import { TestBed } from '@angular/core/testing';

import { UtlisateursService } from './utlisateurs.service';

describe('UtlisateursService', () => {
  let service: UtlisateursService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UtlisateursService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
