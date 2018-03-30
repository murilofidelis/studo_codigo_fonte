import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashbordComponent } from './dashbord/dashbord.component';
import { ChartModule } from 'primeng/components/chart/chart';

@NgModule({
  imports: [
    CommonModule,
    ChartModule
  ],
  declarations: [DashbordComponent]
})
export class DashbordModule { }
