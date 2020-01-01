import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/app.state';
import { Observable } from 'rxjs';
import { Cart } from 'src/app/_models/cart.model';
import * as CartActions from "../../_actions/cart.actions";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  public isMenuCollapsed:boolean = true;
  public showCart:boolean = false;
  userCart : Observable<Cart>;
  baseImageUrl:string = 'https://www.maccosmetics.ca';
  constructor(private store: Store<AppState>) {
    this.userCart = store.select('cart');
   }

  ngOnInit() {
  }

  removeItem(index:number){
    this.store.dispatch(new CartActions.RemoveFromCart(index));
  }


}
