
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})

export class HasRoleGuard   {
 

    constructor(private router: Router,private authService: AuthService) {}

    canActivate (route: ActivatedRouteSnapshot): boolean {
            let hasRole!: boolean;
            let role!: any;
            role =this.authService.getRoleLocal();
            if (route.data['role'].includes(role)) {
            hasRole = true;
            }
            else
            {
              this.router.navigate(['/error']); // Redirige l'utilisateur vers la page d'erreur
              return false;
            }
            return hasRole;
}
  
}
