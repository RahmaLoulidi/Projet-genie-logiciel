import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppelOffreFournisseurComponent } from './appel-offre-fournisseur.component';

describe('AppelOffreFournisseurComponent', () => {
  let component: AppelOffreFournisseurComponent;
  let fixture: ComponentFixture<AppelOffreFournisseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppelOffreFournisseurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppelOffreFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
