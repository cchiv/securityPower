import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    console.log('AuthGuard activated');
    return this.isAdmin();
  }

  isAdmin(): boolean {

    if (JSON.parse(localStorage.getItem('currentUser')) !== null) {
      if (JSON.parse(localStorage.getItem('currentUser')).usertype.toString() === 'ADMIN'
          || JSON.parse(localStorage.getItem('currentUser')).usertype.toString() === 'USER') {
        return true;
      }
    } else {
      console.log('You do not have the authorization to this route');
      return false;
    }
  }
}
