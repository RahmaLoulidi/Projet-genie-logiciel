import { TestBed } from '@angular/core/testing';
import { PropositionFournisseurService } from './proposition-fournisseur.service';



describe('PropositionFournisseurService', () => {
  let service: PropositionFournisseurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PropositionFournisseurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
