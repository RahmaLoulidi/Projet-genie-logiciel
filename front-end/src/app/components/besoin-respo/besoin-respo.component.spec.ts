import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BesoinRespoComponent } from './besoin-respo.component';

describe('BesoinRespoComponent', () => {
  let component: BesoinRespoComponent;
  let fixture: ComponentFixture<BesoinRespoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BesoinRespoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BesoinRespoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
