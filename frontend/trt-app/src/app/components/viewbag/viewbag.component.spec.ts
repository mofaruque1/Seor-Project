import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewbagComponent } from './viewbag.component';

describe('ViewbagComponent', () => {
  let component: ViewbagComponent;
  let fixture: ComponentFixture<ViewbagComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewbagComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewbagComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
