import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-viewbag',
  templateUrl: './viewbag.component.html',
  styleUrls: ['./viewbag.component.css']
})
export class ViewbagComponent implements OnInit {
  productsInTheCart: any[];
  orderSubmitObject: any = {};
  constructor(private productService: ProductService,
    private router: Router) { }

  ngOnInit() {
    this.productsInTheCart = JSON.parse(localStorage.getItem('cart')) || [];
    console.log("view bag : " + this.productsInTheCart.length);
  }

  orderSubmitClicked(name: string, email: string, phoneno: string, address: string) {
    this.orderSubmitObject.customer_name = name;
    this.orderSubmitObject.shipping_address = address;
    this.orderSubmitObject.email = email;
    this.orderSubmitObject.phone = phoneno;
    this.orderSubmitObject.total_product_cost = this.getTotalCost(this.productsInTheCart);
    this.orderSubmitObject.discount_amoun = 0;
    this.orderSubmitObject.shipping_cost = 100;
    this.orderSubmitObject.order = this.productsInTheCart;
    this.productService.submitOrder(this.orderSubmitObject).subscribe((res) => {
      console.log("|--------Order Submit---------|");
      console.log(res);
      
    
      localStorage.removeItem('cart');
      this.router.navigate(['/'])
    },
      (error) => {
        console.log("SOmething went wrong" + error.message);
      })


  }

  getTotalCost(products: any[]): number {
    let total = 0;
    products.forEach(element => {
      total += element.price;
    });
    return total;
  }

}
