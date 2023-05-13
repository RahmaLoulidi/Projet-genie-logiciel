import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanneEnsComponent } from './panne-ens.component';

describe('PanneEnsComponent', () => {
  let component: PanneEnsComponent;
  let fixture: ComponentFixture<PanneEnsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanneEnsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PanneEnsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
