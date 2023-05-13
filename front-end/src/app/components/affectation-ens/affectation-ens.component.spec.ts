import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AffectationEnsComponent } from './affectation-ens.component';

describe('AffectationEnsComponent', () => {
  let component: AffectationEnsComponent;
  let fixture: ComponentFixture<AffectationEnsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AffectationEnsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AffectationEnsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
