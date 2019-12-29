import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './_components/header/header.component';
import { FooterComponent } from './_components/footer/footer.component';
import { BannerComponent } from './_components/banner/banner.component';
import { ProductsComponent } from './_components/products/products.component';
import { ErrorPageComponent } from './_components/error-page/error-page.component';
import { MacLandingPageComponent } from './_components/mac-landing-page/mac-landing-page.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    BannerComponent,
    ProductsComponent,
    ErrorPageComponent,
    MacLandingPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule 

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
