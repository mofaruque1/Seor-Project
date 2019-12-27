import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-landingpage',
  templateUrl: './landingpage.component.html',
  styleUrls: ['./landingpage.component.css']
})
export class LandingpageComponent implements OnInit {

  noOfProductsInTheCart: number;
  productsInTheCart: any[];
  totalPrice: number = 0;
  constructor() { }

  ngOnInit() {
    this.productsInTheCart = JSON.parse(localStorage.getItem('cart')) || [];
    this.productsInTheCart.forEach(element => {
      this.totalPrice += element.price;
    });
    this.noOfProductsInTheCart = this.productsInTheCart.length || 0;
  }

  setNoOfProductsInTheCart(no: number) {
    this.noOfProductsInTheCart = no;
    console.log("Counter from the landing page");
    console.log(this.noOfProductsInTheCart);
  }

  setProductsInTheCart(products: any[]) {
    this.productsInTheCart = products;
  }

  setTotalPrice(price: number) {
    this.totalPrice = price;
  }
}
