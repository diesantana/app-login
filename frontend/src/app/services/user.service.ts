import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  getUsername(): string | null {
    let userStorage = sessionStorage.getItem('username');
    return userStorage;
  }
}
