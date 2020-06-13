import { TestBed } from '@angular/core/testing';

import { HikerequestService } from './hikerequest.service';

describe('HikerequestService', () => {
  let service: HikerequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HikerequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
