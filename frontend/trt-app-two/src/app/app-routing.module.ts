import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ErrorPageComponent } from './_components/error-page/error-page.component';
import { MacLandingPageComponent } from './_components/mac-landing-page/mac-landing-page.component';


const routes: Routes = [
  { path: '', component: MacLandingPageComponent },
  { path: 'error', component: ErrorPageComponent },
  { path: 'sub', loadChildren: () => import('./_modules/sub/sub.module').then(mod => mod.SubModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
