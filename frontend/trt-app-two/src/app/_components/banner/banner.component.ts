import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.scss']
})
export class BannerComponent implements OnInit {

  @Input() imageUrl: string = 'assets/images/default_banner.png';
  @Input() bannerText : string = 'MAC BD';
  constructor() { }

  ngOnInit() {
  }

}
