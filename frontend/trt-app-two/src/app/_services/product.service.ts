import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }
  getMacProduct(productType: string): Observable<any> {
    return this.http.get("../../assets/mockdata/product.json");
     //return this.http.get("https://u6j4k9gy22.execute-api.ca-central-1.amazonaws.com/dev/mac/"+productType);
  }

  submitOrder(order:any){
    return this.http.post("https://u6j4k9gy22.execute-api.ca-central-1.amazonaws.com/dev/order",order);
  }
}
