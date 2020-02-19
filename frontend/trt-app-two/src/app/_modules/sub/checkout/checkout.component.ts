import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from 'src/app/_models/cart.model';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/app.state';
import * as CartActions from "../../../_actions/cart.actions";
import { Order } from 'src/app/_models/order.model';
import { ProductService } from 'src/app/_services/product.service';

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
  orderDetails: Order;

  missingNameOrPhoneNo: boolean;
  missingShippingAddress: boolean;
  readyForPayment:boolean;

  baseImageUrl: string = 'https://www.maccosmetics.ca';
  constructor(private productService: ProductService,private store: Store<AppState>) {
    this.userCart = store.select('cart');
    this.Math = Math;
  }

  ngOnInit() {
    this.pickupSelected = false;
    this.paymentOption = "0%";
    this.missingNameOrPhoneNo = false;
    this.missingShippingAddress = false;
    this.orderDetails = new Order();
    this.readyForPayment = false;
  }

  removeItem(index: number) {
    this.store.dispatch(new CartActions.RemoveFromCart(index));
  }

  submitOrderClicked(name: string, phoneno: string, email: string, address: string, notes: string) {

    if (name.trim() == "" || phoneno.trim() == "" || phoneno.length < 10) {
      this.missingNameOrPhoneNo = true;
      this.missingShippingAddress = false;
    }
    else if (address.trim() == "" && !this.pickupSelected) {
      this.missingNameOrPhoneNo = false;
      this.missingShippingAddress = true;
    }
    else {
      this.missingNameOrPhoneNo = false;
      this.missingShippingAddress = false;
      this.orderDetails.customer_name = name;
      this.orderDetails.phone = phoneno;
      this.orderDetails.email = email;
      this.orderDetails.shipping_address = address;
      this.orderDetails.city = "Dhaka";
      this.orderDetails.customer_pickup = this.pickupSelected;
      this.orderDetails.order_notes = notes;
      this.orderDetails.discount_amount = 0;
      this.orderDetails.shipping_cost = 100;
      // this.orderDetails.payment_option = this.paymentOption;
      this.userCart.subscribe((res) => {
        this.orderDetails.total_product_cost = res.totalPrice;
        this.orderDetails.totalcost_with_shipping = res.totalCostWithShipping;
        // this.orderDetails.payment_option_amount = this.paymentOption == "50%" ? Math.ceil(res.totalCostWithShipping / 2) : res.totalCostWithShipping;
        // this.orderDetails.payment_option_amount = res.totalCostWithShipping;
        this.orderDetails.order = res.order;
        //this.readyForPayment = true;
        this.submitOrder();
      }, (error) => {
        console.log(error.message);
      })
    }
  }


  submitOrder(){

    console.log("Test : ");
    console.log(this.orderDetails);
    
    
    this.productService.submitOrder(this.orderDetails).subscribe((res) => {
      console.log("|--------Order Submit---------|");
      console.log(res);

    },
      (error) => {
        console.log("SOmething went wrong " + error.message);
      })
  }


  paymentOptionClicked(paymentOption: string) {
    this.paymentOption = paymentOption;
  }

}
