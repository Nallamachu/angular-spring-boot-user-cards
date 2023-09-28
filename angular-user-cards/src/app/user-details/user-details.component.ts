import { Component, OnInit } from '@angular/core';
import { ServerService } from '../server.service';
import { Subject, takeUntil } from 'rxjs';
//import { MatSnackBar } from '@angular/material/snack-bar'
import { Router } from '@angular/router'

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss'],
  providers: [ServerService]
})

export class UserDetailsComponent implements OnInit{

  private ngUnsubscribe: Subject<void> = new Subject<void>();
  userDetails: any;

  constructor(public api : ServerService,
    //public snackBar: MatSnackBar,
    private route: Router){

  }

  ngOnInit() {
    this.getUserDetails();
  }

  getUserDetails(){
    this.api.get('http://localhost:8080/user-list')
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe({
        next: (response: any) => {
          this.userDetails = response
          if(response.error != undefined) {
            console.error('API error occurred in getUserDetails function', response.error);
          }
        },
        error: (error : any) => {
          this.handleComponentError(error)
        }
      });
  }

  ////////////// Error Handling ///////////////////////////////////////
  handleComponentError(error: any) {
    console.log(error, 'handleComponentError');
    if(error.status === 400 && error.error && error.error.errors) {
      for(const key in error.error.errors) {
        if(error.error.errors.hasOwnProperty(key)) {
          const dynamicError = error.error.errors[key];
          // this.snackBar.open(dynamicError.msg, 'x', {
          //   panelClass : ['custom-style'],
          //   verticalPosition: 'bottom'
          // });
          console.log(dynamicError.value);
          console.log(dynamicError.msg);
        }
      }
    } else if(error.status === 401 && error.error.code === 401 && error.error) {
      this.route.navigate(['/']);
      localStorage.clear();
      // this.snackBar.open(error.error.errors.server.msg, 'x', {
      //   panelClass : ['custom-style'],
      //   verticalPosition: 'bottom'
      // });
    } else if(error.status === 500 && error.error && error.error.code === 500 ) {
      const databaseError = error.error.errors.server.msg;
      console.log('500 error ', databaseError);
      // this.snackBar.open(error.error.errors.server.msg, 'x', {
      //   panelClass : ['custom-style'],
      //   verticalPosition: 'bottom'
      // });
    } else {
      console.log('An error occurred. Please try again later.');
      // this.snackBar.open('An error occurred. Please try again later.', 'x', {
      //   panelClass : ['custom-style'],
      //   verticalPosition: 'bottom'
      // });
    }
  }

}

