import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { select, Store } from '@ngrx/store';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { AppState } from '../app.model';
import { Column } from '../common/table/table.model';
import { HikeRequest } from './hikerequest.model';
import { deleteHikeRequest, fetchHikeRequest, saveHikeRequest, updateHikeRequest } from './store/hikerequest.action';
import { selectHikeRequest } from './store/hikerequest.selector';
import { HikeDetails, HikeDetailsComponentUrl } from '../hike-details/hike-details.model';
import { selectHikeDetails } from '../hike-details/store/hike-details.selector';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import {formatDate, DatePipe} from '@angular/common';
import { __values } from 'tslib';
import { HikerequestService } from './hikerequest.service';

@Component({
  selector: 'app-hikerequest',
  templateUrl: './hikerequest.component.html',
  styleUrls: ['./hikerequest.component.scss']
})
export class HikerequestComponent implements OnInit {



  readonly columns: Column[] = [
    { name: 'hikeName', label: 'Hike Name' },
    { name: 'employeeIno', label: 'Employee I Number' },
    { name: 'hikeRequestDate', label: 'Requested Date' },
  ];

  readonly hikeRequestFormControlKeys = {
    id: 'id',
    hikeName: 'hikeName',
    employeeIno: 'employeeIno',
    hikeRequestDate: 'hikeRequestDate'
  }

  hikeRequest: HikeRequest[] = [];
  requestableHikes: HikeDetails[] = [];
  selectedHikeRequest: HikeRequest;
  hikeRequestFormGroup: FormGroup;
  formModalActive = false;
  deleteModalActive = false;
  submitModalActive = false;

  private readonly unsubscribe = new Subject<void>();

  constructor(private readonly store: Store<AppState>, private readonly fb: FormBuilder, private http: HttpClient, private readonly hikeRequestService:HikerequestService) { }

  ngOnInit(): void {
    this.requestableHikes = this.hikeRequestService.getRequestableHikes();
    this.prepareHikeRequestFormGroup();
    this.store.pipe(select(selectHikeRequest), takeUntil(this.unsubscribe)).subscribe(val => this.hikeRequest = val);
    this.store.dispatch(fetchHikeRequest());
    this.hikeRequestFormGroup = new FormGroup({
      //id: new FormControl('', [Validators.required]),
      hikeName: new FormControl('', [Validators.required]),
      employeeIno: new FormControl('', [Validators.pattern(/^i[0-9]{6}$/), Validators.required])
    //  hikeRequestDate: new FormControl('', [Validators.required]) 
    });
  }
    // this.requestableHikes = this.hikeRequestService.getRequestableHikes();

  ngOnDestroy(): void {
    this.unsubscribe.next();
    this.unsubscribe.complete();
  }

  onAddBtnClick(): void {
    this.formModalActive = true;
    this.selectedHikeRequest = null;
    this.requestableHikes = this.hikeRequestService.getRequestableHikes();
    // console.log("Size");
    // console.log(this.requestableHikes);
    // console.log(this.requestableHikes.length);
    for(var hd of this.requestableHikes){
      console.log(hd.name);
    }
    
    // console.log("Hike details: "+this.hikeDetails[0].name);
    // console.log("Ans:");
    // console.log(this.hdObj.name);
  }

  onEditBtnClick(hikeRequest: HikeRequest): void {
    this.selectedHikeRequest = hikeRequest;
    this.formModalActive = true;
    this.hikeRequestFormGroup.patchValue({
      [this.hikeRequestFormControlKeys.employeeIno]: hikeRequest.employeeIno,
      [this.hikeRequestFormControlKeys.hikeName]: hikeRequest.hikeName
    })

  }

  onDeleteBtnClick(hikeRequest: HikeRequest): void {
    this.selectedHikeRequest = hikeRequest;
    this.deleteModalActive = true;

  }

  onFormSubmitBtnClick(): void {
    console.log(this.hikeRequestFormGroup.value)
    if (this.selectedHikeRequest == null) {
      this.store.dispatch(saveHikeRequest({ hikeRequest: { ...this.hikeRequestFormGroup.value, id: null } }));
    }
    else {
      this.store.dispatch(updateHikeRequest({ hikeRequest: { ...this.hikeRequestFormGroup.value, id: this.selectedHikeRequest.id } }));
    }
    this.formModalActive = false;
    this.selectedHikeRequest = null;

  }

  onOptionsChange(item): void{
    // var myDate = new Date();
    // var obj:HikeDetails;
    // for(var hd of this.hikeDetails){
    //   if(hd.id == item){
    //       obj = hd;
    //   }
    // }
    // var expiryDate = new Date(obj.date);
    // if(!(expiryDate>myDate)){
    //   this.submitModalActive = true;
    // }
  }

  onFormCancelBtnClick(): void {
    this.selectedHikeRequest = null;
    this.formModalActive = false;
  }

  onDeleteConfirm(): void {
    this.store.dispatch(deleteHikeRequest({ hikeRequest: this.selectedHikeRequest }));
    this.selectedHikeRequest = null;
    this.deleteModalActive = false;
  }

  onDeleteReject(): void {
    this.deleteModalActive = false;
    this.selectedHikeRequest = null;
  }

  onOk(): void{
    this.submitModalActive = false;
    this.formModalActive = false;
  }

  private prepareHikeRequestFormGroup(): void {
    this.hikeRequestFormGroup = this.fb.group({
      [this.hikeRequestFormControlKeys.id]: this.fb.control(''),
      [this.hikeRequestFormControlKeys.hikeName]: this.fb.control(''),
      [this.hikeRequestFormControlKeys.employeeIno]: this.fb.control(''),
      [this.hikeRequestFormControlKeys.hikeRequestDate]: this.fb.control('')
    });
  }
}
