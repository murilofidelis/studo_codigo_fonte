import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http';

import { DisciplinaModule } from './view/disciplina/disciplina.module';
import { DisciplinaService } from './service/disciplina.service';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    DisciplinaModule
  ],
  providers: [DisciplinaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
