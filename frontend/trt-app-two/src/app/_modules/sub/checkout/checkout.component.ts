import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from 'src/app/_models/cart.model';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/app.state';
import * as CartActions from "../../../_actions/cart.actions";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {

  userCart: Observable<Cart>;
  Math: any;
  pickupSelected: boolean;
  paymentOption: string;

  missingNameOrPhoneNo:boolean;
  missingShippingAddress:boolean;

  baseImageUrl: string = 'https://www.maccosmetics.ca';
  constructor(private store: Store<AppState>) {
    this.userCart = store.select('cart');
    this.Math = Math;
  }

  ngOnInit() {
    this.pickupSelected = false;
    this.paymentOption = "50%";
    this.missingNameOrPhoneNo = false;
    this.missingShippingAddress = false;

  }

  removeItem(index: number) {
    this.store.dispatch(new CartActions.RemoveFromCart(index));
  }

  continueToPaymentClicked(name: string, phoneno: string, email: string, address: string, notes: string) {

    
    if (name.trim()=="" || phoneno.trim()=="" || phoneno.length<10) {
      this.missingNameOrPhoneNo = true;
      this.missingShippingAddress = false;
    }
    else if(address.trim()=="" && !this.pickupSelected){
      this.missingNameOrPhoneNo = false;
      this.missingShippingAddress = true;
    }
    else{
      this.missingNameOrPhoneNo = false;
      this.missingShippingAddress = false;
      console.log("|------Info-------|");
      console.log("name : " + name);
      console.log("phone no : " + phoneno);
      console.log("email : " + email);
      console.log("address : " + address);
      console.log("notes : " + notes);
      console.log("picking up : " + this.pickupSelected);
      console.log("Payment option : "+this.paymentOption);
    }

    
    
  }

  paymentOptionClicked(paymentOption: string) {
    this.paymentOption = paymentOption;
  }

}
