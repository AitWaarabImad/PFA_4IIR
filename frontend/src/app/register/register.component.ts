import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { AuthService } from "../auth.service";
import {Router} from "@angular/router";

// Fonction de validation modifiée pour être synchrone
export function matchOtherValidator(otherControlName: string) {
  let thisControl: FormControl;
  let otherControl: FormControl;

  return function matchOtherValidate(control: FormControl) {
    if (!control.parent) {
      return null;
    }

    // Initializing the validator.
    if (!thisControl) {
      thisControl = control;
      otherControl = control.parent.get(otherControlName) as FormControl;
      if (!otherControl) {
        throw new Error('matchOtherValidator(): other control is not found in parent group');
      }
      otherControl.valueChanges.subscribe(() => {
        thisControl.updateValueAndValidity();
      });
    }

    if (!otherControl) {
      return null;
    }

    if (otherControl.value !== thisControl.value) {
      return {
        matchOther: true
      };
    }

    return null;
  };
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerFormGroup: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService,private route: Router) {
    this.registerFormGroup = this.fb.group({
      username: [null, Validators.required],
      nom: [null, Validators.required],
      prenom: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required, Validators.minLength(3)]],
      // Utilisation du validateur modifié
      password2: [null, [Validators.required, Validators.minLength(3), matchOtherValidator("password")]]
    });
  }

  register() {
    if (this.registerFormGroup.invalid) {
      return;
    }

    const user = {
      username: this.registerFormGroup.value.username,
      nom: this.registerFormGroup.value.nom,
      prenom: this.registerFormGroup.value.prenom,
      email: this.registerFormGroup.value.email,
      password: this.registerFormGroup.value.password
    };

    this.authService.register(user).subscribe(
      response => {
        console.log('User registered successfully!', response);
        this.route.navigateByUrl("/");
      },
      error => {
        console.error('Registration failed:', error);
      }
    );
  }
}
