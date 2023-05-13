import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropositionFournisseurComponent } from './proposition-fournisseur.component';

describe('PropositionFournisseurComponent', () => {
  let component: PropositionFournisseurComponent;
  let fixture: ComponentFixture<PropositionFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PropositionFournisseurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PropositionFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
