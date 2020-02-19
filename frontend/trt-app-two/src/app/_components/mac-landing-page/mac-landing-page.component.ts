import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/_services/product.service';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/app.state';
import { Observable } from 'rxjs';
import { Cart } from 'src/app/_models/cart.model';
import * as CartActions from "../../_actions/cart.actions";
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-mac-landing-page',
  templateUrl: './mac-landing-page.component.html',
  styleUrls: ['./mac-landing-page.component.scss']
})
export class MacLandingPageComponent implements OnInit {

  products: any[] = [];
  qvProductContent: any = {};
  baseImageurl: string = "https://www.maccosmetics.ca";
  largeImage: string;
  activeThumb: number;
  showMore:boolean;
  bannerText:string;

  userCart: Observable<Cart>;

  constructor(
    private productService: ProductService,
    private store: Store<AppState>,
    private modalService: NgbModal) {
    this.userCart = store.select('cart');
  }

  ngOnInit() {
    //localStorage.removeItem('order'); //reset
    this.getProducts("assorted");
    this.showMore = false;
  }

 getProducts(productType: string) {
   this.bannerText = productType == "assorted" ? "MAC BD" : productType;
    this.productService.getMacProduct(productType).subscribe((res) => {
      this.products = res;
      let length = this.products.length;
      for (let index = 0; index < length; index++) {
        let temp = this.baseImageurl + this.products[index].large_image_url;
        this.products[index].large_image_url = temp;
      }
    })
  }

  
  addToCart(item: any) {
    this.store.dispatch(new CartActions.AddToCart(item));
  }
/*
* Render Product Details
* @input: content(string), item(object)
* @return: null
*/
  productDetails(content: any, item: any) {
    this.activeThumb = 0;
    this.qvProductContent = item;
    this.largeImage = item.large_image_url;
    this.modalService.open(content, { size: 'xl' });
  }


  /*
  switch image
  @input: imageURL
  @return: null
  */
  changeImage(imageURL: string, selectedItem: number) {
    this.activeThumb = selectedItem;
    this.largeImage = this.baseImageurl + imageURL;
  }

  isActive(item: any) {
    return this.activeThumb === item;
  };



}
