import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BesoinLivrableComponent } from './besoin-livrable.component';

describe('BesoinLivrableComponent', () => {
  let component: BesoinLivrableComponent;
  let fixture: ComponentFixture<BesoinLivrableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BesoinLivrableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BesoinLivrableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
