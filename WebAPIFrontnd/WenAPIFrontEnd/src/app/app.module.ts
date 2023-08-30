import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RewardDetailsComponent } from './components/reward-details/reward-details.component';
import { RewardService } from 'src/app/services/reward.service';

@NgModule({
  declarations: [
    AppComponent,
    RewardDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [RewardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
