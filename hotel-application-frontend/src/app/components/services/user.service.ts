import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginDTO, RegisterDTO, User } from '../models/user.mode';
import { environment } from '../../../environments/environment.development';
import { ReplaySubject, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  private userSource = new ReplaySubject<User | null>(1);
  user$ = this.userSource.asObservable();

  addNewUser(newUser: RegisterDTO) {
    return this.http.post<RegisterDTO>(environment.apiUrl + "api/users/add-user", newUser);
  }

  loginUser(user: LoginDTO) {
    return this.http.post<User>(environment.apiUrl + "api/users/login-user", user).pipe(
      map((user: User) => {
        if(user) {
          this.setUser(user);
        }
      })
    );
  }

  private setUser(user: User) {
    sessionStorage.setItem("currentUser", JSON.stringify(user));
    this.userSource.next(user);

    this.user$.subscribe({
      next: re => console.log(re)
    })
  }
}
