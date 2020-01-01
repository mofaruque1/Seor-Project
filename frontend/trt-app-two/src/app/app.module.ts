import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { StoreModule } from "@ngrx/store";
import { cartReducer } from "./_reducer/cart.reducer";



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './_components/header/header.component';
import { FooterComponent } from './_components/footer/footer.component';
import { BannerComponent } from './_components/banner/banner.component';
import { ErrorPageComponent } from './_components/error-page/error-page.component';
import { MacLandingPageComponent } from './_components/mac-landing-page/mac-landing-page.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    BannerComponent,
    ErrorPageComponent,
    MacLandingPageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    StoreModule.forRoot({cart : cartReducer}) 

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
