import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HelperRoutingModule } from './helper-routing.module';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { CheckoutComponent } from './checkout/checkout.component';


@NgModule({
  declarations: [
    ContactUsComponent, 
    CheckoutComponent
  ],
  imports: [
    CommonModule,
    HelperRoutingModule
  ]
})
export class HelperModule { }
