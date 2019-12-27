import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ViewbagComponent } from './components/viewbag/viewbag.component';
import { LandingpageComponent } from './components/landingpage/landingpage.component';


const routes: Routes = [
  {path:'',component:LandingpageComponent},
  {path:'viewbag',component:ViewbagComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
