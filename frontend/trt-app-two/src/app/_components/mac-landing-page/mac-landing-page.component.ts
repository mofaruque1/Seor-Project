import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/_services/product.service';

@Component({
  selector: 'app-mac-landing-page',
  templateUrl: './mac-landing-page.component.html',
  styleUrls: ['./mac-landing-page.component.scss']
})
export class MacLandingPageComponent implements OnInit {

  products: any[] = [];
  baseImageurl: string = "https://www.maccosmetics.ca";

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.getProducts("assorted");
  }

  private getProducts(productType: string) {
    this.productService.getMacProduct(productType).subscribe((res) => {
      this.products = res;
      let length = this.products.length;
      for (let index = 0; index < length; index++) {
        let temp = this.baseImageurl + this.products[index].large_image_url;
        this.products[index].large_image_url = temp;
      }
    })
  }



}
