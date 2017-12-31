import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastyModule } from 'ng2-toasty';

import { DisciplinaModule } from './view/disciplina/disciplina.module';
import { DisciplinaService } from './service/disciplina.service';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpModule,
    ToastyModule.forRoot(),

    DisciplinaModule
  ],
  providers: [DisciplinaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
