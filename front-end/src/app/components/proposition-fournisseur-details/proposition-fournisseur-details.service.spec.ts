import { TestBed } from '@angular/core/testing';

import { PropositionFournisseurDetailsService } from './proposition-fournisseur-details.service';

describe('PropositionFournisseurDetailsService', () => {
  let service: PropositionFournisseurDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PropositionFournisseurDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
