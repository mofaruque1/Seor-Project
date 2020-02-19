import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { SubRoutingModule } from './sub-routing.module';
import { CheckoutComponent } from './checkout/checkout.component';
import { OrderSubmitSuccessComponent } from './order-submit-success/order-submit-success.component';


@NgModule({
  declarations: [
    CheckoutComponent,
    OrderSubmitSuccessComponent
  ],
  imports: [
    CommonModule,
    SubRoutingModule,
    FormsModule
  ]
})
export class SubModule { }
