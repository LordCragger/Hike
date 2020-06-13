import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HikeDetailsComponentUrl, HikeDetails } from '../hike-details/hike-details.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HikerequestService {

  hikeDetails: HikeDetails[] = [];
  constructor(private readonly http: HttpClient) {
    // var hikeDetails:HikeDetails[] = [];
    //  var requestableHikes:HikeDetails[];
    //  var date = new Date();
    const url = `${environment.apiBasePath}${HikeDetailsComponentUrl.FETCH_HIKEDETAILS}`;
    console.log("Service ko");
    console.log("url: " + url);
    this.http.get<HikeDetails[]>(url).subscribe(val => {
      this.hikeDetails = val;
    });

  }

  getRequestableHikes(): HikeDetails[] {
    var requestableHikes: HikeDetails[] = [];
    var date = new Date();
    // const url = `${environment.apiBasePath}${HikeDetailsComponentUrl.FETCH_HIKEDETAILS}`;
    // console.log("Service ko");
    // console.log("url: "+url);
    // this.http.get<HikeDetails[]>(url).subscribe(val=>{
    //   this.hikeDetails = val;
    // });
    var i = 0;
    console.log(this.hikeDetails.length);
    for (var hd of this.hikeDetails) {
      console.log("Date Check");
      if (hd.hikeRequestDate.toString() === date.toISOString().slice(0, 10)) {
        // console.log("Hike Requestable");
        requestableHikes[i] = hd;
        console.log("I ko val");
        console.log(i);
        i++;
      }
    }
    return requestableHikes;
  }




}
