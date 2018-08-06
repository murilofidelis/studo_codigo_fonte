import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashbordComponent } from './dashbord/dashbord.component';
import { ChartModule } from 'primeng/components/chart/chart';
import { ButtonModule } from 'primeng/components/button/button';

@NgModule({
  imports: [
    CommonModule,
    ChartModule,
    ButtonModule
  ],
  declarations: [DashbordComponent]
})
export class DashbordModule { }
