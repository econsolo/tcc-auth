import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material-module/material.module';
import { ExternalComponent } from './external/external.component';
import { ExternalModule } from './external/external.module';
import { routing } from './app.route';

@NgModule({
  declarations: [
    AppComponent,
    ExternalComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    routing,
    MaterialModule,
    ExternalModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
