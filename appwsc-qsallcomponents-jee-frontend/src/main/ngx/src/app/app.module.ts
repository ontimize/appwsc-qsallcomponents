import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ServiceWorkerModule } from '@angular/service-worker';
import {
  APP_CONFIG,
  O_AUTH_SERVICE,
  O_INPUTS_OPTIONS,
  O_MAT_ERROR_OPTIONS,
  ONTIMIZE_MODULES,
  ONTIMIZE_PROVIDERS,
  OntimizeWebModule,
} from 'ontimize-web-ngx';
import { KeycloakOptions, O_KEYCLOAK_OPTIONS, OKeycloakAuthService, OntimizeKeycloakModule } from 'ontimize-web-ngx-keycloak';

import { environment } from '../environments/environment';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CONFIG } from './app.config';
import { D3LocaleService } from './shared/d3-locale/d3Locale.service';
import { StyleManager } from './shared/style-manager/style-manager';
import { ThemeService } from './shared/theme.service';

const keycloakOptions: KeycloakOptions = {
  config: {
    url: 'http://34.141.225.85/auth',
    realm: 'keycloak-demo',
    clientId: 'keycloak-demo-ui'
  },
  initOptions: {
    onLoad: 'login-required'
  }
};

// Defining custom providers (if needed)...
export const customProviders: any = [
  D3LocaleService,
  StyleManager,
  ThemeService,
  { provide: O_AUTH_SERVICE, useValue: OKeycloakAuthService },
  { provide: O_KEYCLOAK_OPTIONS, useValue: keycloakOptions },
  { provide: O_MAT_ERROR_OPTIONS, useValue: { type: 'lite' } },
  { provide: O_INPUTS_OPTIONS, useValue: { iconColor: 'accent' } }
];


@NgModule({
  declarations: [AppComponent],
  imports: [
    ONTIMIZE_MODULES,
    OntimizeWebModule,
    AppRoutingModule,
    HttpClientModule,
    ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production }),
    OntimizeKeycloakModule
  ],
  providers: [
    { provide: APP_CONFIG, useValue: CONFIG },
    ...ONTIMIZE_PROVIDERS,
    ...customProviders
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
