import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPropositionFournisseurComponent } from './add-proposition-fournisseur.component';

describe('AddPropositionFournisseurComponent', () => {
  let component: AddPropositionFournisseurComponent;
  let fixture: ComponentFixture<AddPropositionFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPropositionFournisseurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPropositionFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
