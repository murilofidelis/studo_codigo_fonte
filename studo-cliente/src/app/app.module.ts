import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { DisciplinaModule } from './view/disciplina/disciplina.module';
import { TurmaModule } from './view/turma/turma.module';
import { CoreModule } from './core/core.module';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpModule,

    CoreModule,
    DisciplinaModule,
    TurmaModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
