import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UIElementsComponent } from './ui-elements.component';

describe('UIElementsComponent', () => {
  let component: UIElementsComponent;
  let fixture: ComponentFixture<UIElementsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UIElementsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UIElementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
