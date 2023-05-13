import { TestBed } from '@angular/core/testing';

import { AppelOffreFournisseurService } from './appel-offre-fournisseur.service';

describe('AppelOffreFournisseurService', () => {
  let service: AppelOffreFournisseurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppelOffreFournisseurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
