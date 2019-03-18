import { 
    MatButtonModule, 
    MatCheckboxModule, 
    MatSnackBarModule, 
    MatCardModule, 
    MatInputModule, 
    MatIconModule 
} from '@angular/material';
import { NgModule } from '@angular/core';

@NgModule({
    imports: [
        MatButtonModule, 
        MatCheckboxModule,
        MatSnackBarModule,
        MatCardModule,
        MatInputModule,
        MatIconModule
    ],
    exports: [
        MatButtonModule, 
        MatCheckboxModule,
        MatSnackBarModule,
        MatCardModule,
        MatInputModule,
        MatIconModule
    ],
})
export class MaterialModule { }