import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropositionFournisseurDetailsComponent } from './proposition-fournisseur-details.component';

describe('PropositionFournisseurDetailsComponent', () => {
  let component: PropositionFournisseurDetailsComponent;
  let fixture: ComponentFixture<PropositionFournisseurDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PropositionFournisseurDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PropositionFournisseurDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
