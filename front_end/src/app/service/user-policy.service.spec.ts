import { TestBed } from '@angular/core/testing';

import { UserPolicyService } from './user-policies.service';

describe('UserPolicyService', () => {
  let service: UserPolicyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserPolicyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
