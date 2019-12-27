import { Component, OnInit, Input } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Input() noOfProductsInTheCart: number;
  @Input() productsInTheCart: any[];
  showCart: boolean = false;
  @Input() totalPrice: number;
  constructor(private productService: ProductService) { }

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
    let tempCart= [];
    this.totalPrice = 0;
    this.productsInTheCart.forEach((element,i) => {
      if (i != index) {
        tempCart.push(this.productsInTheCart[i]);
      }
    });
    this.productsInTheCart = tempCart;
    this.productsInTheCart.forEach((element) => {
      this.totalPrice  +=element.price;
    });

    localStorage.setItem('cart',JSON.stringify(this.productsInTheCart))
    this.noOfProductsInTheCart = this.productsInTheCart.length;
  }


}
