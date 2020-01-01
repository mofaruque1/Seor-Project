import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { SubRoutingModule } from './sub-routing.module';
import { CheckoutComponent } from './checkout/checkout.component';


@NgModule({
  declarations: [
    CheckoutComponent
  ],
  imports: [
    CommonModule,
    SubRoutingModule,
    FormsModule
  ]
})
export class SubModule { }
