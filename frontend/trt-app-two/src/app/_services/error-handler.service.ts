import { Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

  public errorMessage: string = '';
  constructor() { }

  public handleError(error: HttpErrorResponse, compAndFunName: string) {

    console.log('%c Error occured from ----> ' + compAndFunName, 'background: #222; color: #bada55');
    console.log('%c Error message : ' + error.message, 'background: #222; color: #bada55');
    console.log('%c Error status code : ' + error.status, 'background: #222; color: #bada55');

    if (error.status === 500) {
      this.handle500Error(error);
    }
    else if (error.status === 404) {
      this.handle404Error(error)
    }
    else {
      this.handleOtherError(error);
    }
  }

  private handle500Error(error: HttpErrorResponse) {
    this.createErrorMessage(error);
  }

  private handle404Error(error: HttpErrorResponse) {
    this.createErrorMessage(error);
  }

  private handleOtherError(error: HttpErrorResponse) {
    this.createErrorMessage(error);
  }

  private createErrorMessage(error: HttpErrorResponse) {
    this.errorMessage = error.error ? error.error : error.statusText;
    console.log(this.errorMessage);
    
  }
}
