import { Routes, RouterModule } from '@angular/router';
import { ExternalComponent } from './external/external.component';
import { MODULE_ROUTES_EXTERNAL } from './external/external.route';

const APP_ROUTES: Routes = [
    {
        path: '', component: ExternalComponent,
        children: MODULE_ROUTES_EXTERNAL
    },
    
    { path: '**', redirectTo: '', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(APP_ROUTES);
