import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderSubmitSuccessComponent } from './order-submit-success.component';

describe('OrderSubmitSuccessComponent', () => {
  let component: OrderSubmitSuccessComponent;
  let fixture: ComponentFixture<OrderSubmitSuccessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderSubmitSuccessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderSubmitSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
