import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  GuardResult,
  MaybeAsync,
  Router,
  RouterStateSnapshot
} from '@angular/router';
import {AuthService} from "../auth.service";
import {state} from "@angular/animations";
import {Injectable} from "@angular/core";

@Injectable()
export class AuthGuard
{
  constructor(private authService: AuthService,private route:Router) {}
    canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot): MaybeAsync<GuardResult>
    {
      if(this.authService.isLoggedin)
      {
        return true;
      }
      else
      {
        this.route.navigateByUrl("/login");
        return false;
      }
    }

}
