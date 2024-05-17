import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, ReactiveFormsModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {
  errorMessages: string[] = [];
  loginForm: FormGroup = new FormGroup({})

  constructor(private userService: UserService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.initializaForm();
  }

  initializaForm() {
    this.loginForm = this.formBuilder.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]]
    });
  }

  login() {
    if(this.loginForm.valid) {
      this.userService.loginUser(this.loginForm.value).subscribe({
        next: (value) => {
          console.log(value);
        },
        error: (err) => {
          this.errorMessages.pop();
          this.errorMessages.push(err.error.message);
        },
      })
    } else {
      this.errorMessages.pop();
      this.errorMessages.push("Ambele campuri trebuie completate!");
    }
  }

  
}
