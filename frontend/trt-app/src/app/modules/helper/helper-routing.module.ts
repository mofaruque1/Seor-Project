import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContactUsComponent } from "./contact-us/contact-us.component";
import { CheckoutComponent } from './checkout/checkout.component';



const routes: Routes = [
  { path: 'contactus', component: ContactUsComponent},
  { path: 'checkout', component: CheckoutComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HelperRoutingModule { }