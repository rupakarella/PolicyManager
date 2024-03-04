import { PlatformLocation } from '@angular/common';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavigationService {
  constructor(private platformlocation: PlatformLocation) {}
  disableBackButton() {
    history.pushState(null,'',location.href);
    this.platformlocation.onPopState(() => {
      history.pushState(null,'',location.href);
    });
  }
}