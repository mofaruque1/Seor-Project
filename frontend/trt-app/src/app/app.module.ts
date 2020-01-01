import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { SharedCompModule } from "./modules/shared-comp/shared-comp.module";


import { StoreModule } from "@ngrx/store";
import { cartReducer } from "./reducer/cart.reducer";


import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { BannerComponent } from './components/banner/banner.component';
import { FooterComponent } from './components/footer/footer.component';
import { ProductsComponent } from './components/products/products.component';
import { LandingpageComponent } from './components/landingpage/landingpage.component';
import { ViewbagComponent } from './components/viewbag/viewbag.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BannerComponent,
    FooterComponent,
    ProductsComponent,
    LandingpageComponent,
    ViewbagComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    SharedCompModule,
    StoreModule.forRoot({cart : cartReducer})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
