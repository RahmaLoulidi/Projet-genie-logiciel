import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BesoinAffComponent } from './besoin-aff.component';

describe('BesoinAffComponent', () => {
  let component: BesoinAffComponent;
  let fixture: ComponentFixture<BesoinAffComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BesoinAffComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BesoinAffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
