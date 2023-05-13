import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropositionResponsableComponent } from './proposition-responsable.component';

describe('PropositionResponsableComponent', () => {
  let component: PropositionResponsableComponent;
  let fixture: ComponentFixture<PropositionResponsableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PropositionResponsableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PropositionResponsableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
