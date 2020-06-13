import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HikerequestComponent } from './hikerequest.component';

describe('HikerequestComponent', () => {
  let component: HikerequestComponent;
  let fixture: ComponentFixture<HikerequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HikerequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HikerequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
