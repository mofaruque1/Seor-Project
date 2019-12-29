import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MacLandingPageComponent } from './mac-landing-page.component';

describe('MacLandingPageComponent', () => {
  let component: MacLandingPageComponent;
  let fixture: ComponentFixture<MacLandingPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MacLandingPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MacLandingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
