import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ViewbagComponent } from './components/viewbag/viewbag.component';
import { LandingpageComponent } from './components/landingpage/landingpage.component';


const routes: Routes = [
  {path:'',component:LandingpageComponent},
  {path:'viewbag',component:ViewbagComponent},
  {path:'helper',loadChildren:()=>import('./modules/helper/helper.module').then(mod => mod.HelperModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
