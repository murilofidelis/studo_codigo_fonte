import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import { ROUTES } from './app.routes';

import { TurmaModule } from './view/turma/turma.module';
import { CoreModule } from './core/core.module';
import { ProfessorModule } from './view/professor/professor.module';



@NgModule({
  declarations: [
    AppComponent

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpModule,
    RouterModule.forRoot(ROUTES),

    /*Módulos que serão carregados na inicialização da aplicação*/
    CoreModule,
    TurmaModule,
    ProfessorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
