import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  @Output() noOfProductsInTheCartChange = new EventEmitter();
  @Output() productsInTheCartChange = new EventEmitter();
  @Output() totalPriceChange = new EventEmitter();

  noOfProductsInTheCart: number = 0;
  totalPrice:number = 0;
  products: any[] = [];
  cartProducts: any[] = [];

  imageurl: string = "https://www.maccosmetics.ca";
  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.getProducts("lipgloss");
    this.cartProducts = JSON.parse(localStorage.getItem('cart')) || [];
    this.noOfProductsInTheCart = this.cartProducts.length;
  }

  private getProducts(productType: string) {
    this.productService.getMacProduct(productType).subscribe((res) => {
      this.products = res;
      for (let index = 0; index < this.products.length; index++) {
        let temp = this.imageurl + this.products[index].large_image_url;
        this.products[index].large_image_url = temp;
      }
    })
  }

  addToTheCart(index: number) {
    console.log("product no : " + index);
    this.totalPrice = 0;

    this.cartProducts = JSON.parse(localStorage.getItem('cart')) || [];

    this.cartProducts.push(this.products[index]);

    this.cartProducts.forEach(element => {
      this.totalPrice +=element.price;
    });
    localStorage.setItem('cart', JSON.stringify(this.cartProducts));
    this.noOfProductsInTheCart = this.cartProducts.length;
    this.noOfProductsInTheCartChange.emit(this.noOfProductsInTheCart);
    this.productsInTheCartChange.emit(this.cartProducts);
    this.totalPriceChange.emit(this.totalPrice);
    
  }

}
