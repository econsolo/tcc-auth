import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { registerLocaleData } from '@angular/common';
import { MODULE_COMPONENTS_EXTERNAL, MODULE_SERVICES_EXTERNAL } from './external.route';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from '../material-module/material.module';
import { UtilModule } from '../util-module/util.module';


@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    MaterialModule,
    UtilModule,
    RouterModule.forChild([])
  ],
  exports: [
    MODULE_COMPONENTS_EXTERNAL
  ],
  declarations: [
    MODULE_COMPONENTS_EXTERNAL
  ],
  providers: [
    MODULE_SERVICES_EXTERNAL
  ]
})

export class ExternalModule {

}
