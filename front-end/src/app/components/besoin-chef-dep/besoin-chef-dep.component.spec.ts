import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BesoinChefDepComponent } from './besoin-chef-dep.component';

describe('BesoinChefDepComponent', () => {
  let component: BesoinChefDepComponent;
  let fixture: ComponentFixture<BesoinChefDepComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BesoinChefDepComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BesoinChefDepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
