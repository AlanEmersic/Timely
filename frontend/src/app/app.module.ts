import { NgModule, LOCALE_ID } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProjectComponent } from './project/project/project.component';
import { ProjectPipe } from './project/project.pipe';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import localeHr from '@angular/common/locales/hr';

registerLocaleData(localeHr);

@NgModule({
  declarations: [AppComponent, ProjectComponent, ProjectPipe],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [{ provide: LOCALE_ID, useValue: 'hr-Hr' }],
  bootstrap: [AppComponent],
})
export class AppModule {}
