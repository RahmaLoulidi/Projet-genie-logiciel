import { TestBed } from '@angular/core/testing';

import { AddPropositionFournisseurService } from './add-proposition-fournisseur.service';

describe('AddPropositionFournisseurService', () => {
  let service: AddPropositionFournisseurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddPropositionFournisseurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
