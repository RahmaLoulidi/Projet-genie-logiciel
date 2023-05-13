import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConstatRespoComponent } from './constat-respo.component';

describe('ConstatRespoComponent', () => {
  let component: ConstatRespoComponent;
  let fixture: ComponentFixture<ConstatRespoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConstatRespoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConstatRespoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
