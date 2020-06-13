import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { createEffect, ofType, Actions } from '@ngrx/effects';
import { EMPTY } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { HikeRequestComponentAction, HikeRequestComponentUrl, HikeRequest } from '../hikerequest.model';
import { setHikeRequest, fetchHikeRequest } from './hikerequest.action';

@Injectable()
export class hikeRequestEffect {

    constructor(private readonly action$: Actions, private readonly http: HttpClient) { }

    fetchHikeRequest$ = createEffect(() => {
        return this.action$.pipe(
            ofType(HikeRequestComponentAction.FETCH_HIKEREQUEST),
            mergeMap(() => {
                const url = `${environment.apiBasePath}${HikeRequestComponentUrl.FETCH_HIKEREQUEST}`;
                return this.http.get<HikeRequest[]>(url).pipe(
                    map(val => setHikeRequest({ hikeRequest: val })),
                    catchError(() => EMPTY)
                );
            })
        )
    });

    saveHikeRequest$ = createEffect(() => {
        return this.action$.pipe(
            ofType(HikeRequestComponentAction.SAVE_HIKEREQUEST),
            mergeMap((action: any) => {
                const url = `${environment.apiBasePath}${HikeRequestComponentUrl.SAVE_HIKEREQUEST}`;
                console.log("url: "+url);
                console.log("post");
                console.log(action.hikeRequest);
                return this.http.post<HikeRequest>(url, action.hikeRequest).pipe(
                    map(() => fetchHikeRequest()),
                    catchError(() => EMPTY)
                );
            })
        )
    });

    updateHikeRequest$ = createEffect(() => {
        return this.action$.pipe(
            ofType(HikeRequestComponentAction.UPDATE_HIKEREQUEST),
            mergeMap((action: any) => {
                const url = `${environment.apiBasePath}${HikeRequestComponentUrl.UPDATE_HIKEREQUEST}`;
                return this.http.put<HikeRequest>(url, action.hikeRequest).pipe(
                    map(() => fetchHikeRequest()),
                    catchError(() => EMPTY)
                );
            })
        )
    });

    deleteHikeRequest$ = createEffect(() => {
        return this.action$.pipe(
            ofType(HikeRequestComponentAction.DELETE_HIKEREQUEST),
            mergeMap((action: any) => {
                const url = `${environment.apiBasePath}${HikeRequestComponentUrl.DELETE_HIKEREQUEST}/${action.hikeRequest.id}`;
                return this.http.delete<HikeRequest>(url).pipe(
                    map(() => fetchHikeRequest()),
                    catchError(() => EMPTY)
                );
            })
        )
    });




}