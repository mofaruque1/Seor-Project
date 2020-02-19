import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CheckoutComponent } from './checkout/checkout.component';
import { OrderSubmitSuccessComponent } from "./order-submit-success/order-submit-success.component";


const routes: Routes = [
  { path: 'checkout', component: CheckoutComponent },
  { path: 'order-success', component: OrderSubmitSuccessComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubRoutingModule { }
