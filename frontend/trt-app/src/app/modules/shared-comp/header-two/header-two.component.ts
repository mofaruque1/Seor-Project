import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from 'src/app/models/cart.model';
import { ProductService } from 'src/app/services/product.service';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/app.state';

import * as CartActions from "../../../actions/cart.actions";

@Component({
  selector: 'app-header-two',
  templateUrl: './header-two.component.html',
  styleUrls: ['./header-two.component.css']
})
export class HeaderTwoComponent implements OnInit {

//redux
userCart : Observable<Cart>;

  @Input() noOfProductsInTheCart: number;
  @Input() productsInTheCart: any[];
  showCart: boolean = false;
  @Input() totalPrice: number;
  constructor(private productService: ProductService,private store: Store<AppState>) { 
    this.userCart = store.select('cart');
  }

  ngOnInit() {
    //this.totalPrice = 0;
  }

  showCartClicked(){
    this.showCart =!this.showCart;
    // this.productsInTheCart.forEach(element => {
    //   this.totalPrice += element.price;
    // });
  }

  removeItem(index:number){
    // let tempCart= [];
    // this.totalPrice = 0;
    // this.productsInTheCart.forEach((element,i) => {
    //   if (i != index) {
    //     tempCart.push(this.productsInTheCart[i]);
    //   }
    // });
    // this.productsInTheCart = tempCart;
    // this.productsInTheCart.forEach((element) => {
    //   this.totalPrice  +=element.price;
    // });

    // localStorage.setItem('cart',JSON.stringify(this.productsInTheCart))
    // this.noOfProductsInTheCart = this.productsInTheCart.length;

    this.store.dispatch(new CartActions.RemoveFromCart(index));
  }

}
