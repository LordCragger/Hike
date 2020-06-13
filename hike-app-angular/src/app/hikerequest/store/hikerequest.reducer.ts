import { HikeRequestComponentState, HikeRequestComponentAction } from '../hikerequest.model';
import { createReducer, on } from '@ngrx/store';
import { setHikeRequest } from './hikerequest.action';

export const hikeRequestComponentInitialState: HikeRequestComponentState = {
    hikeRequest: [],

};

export const hikeRequestComponentReducer = createReducer(hikeRequestComponentInitialState,
    on(setHikeRequest, (state, action) => ({ ...state, hikeRequest: action.hikeRequest }))
);