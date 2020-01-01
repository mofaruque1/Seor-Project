import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedCompRoutingModule } from './shared-comp-routing.module';
import { HeaderTwoComponent } from './header-two/header-two.component';


@NgModule({
  declarations: [HeaderTwoComponent],
  imports: [
    CommonModule,
    SharedCompRoutingModule
  ]
})
export class SharedCompModule { }
