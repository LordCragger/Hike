import { createAction, props } from '@ngrx/store';
import { HikeRequestComponentAction, HikeRequest } from '../hikerequest.model';

export const fetchHikeRequest = createAction(HikeRequestComponentAction.FETCH_HIKEREQUEST);
export const setHikeRequest = createAction(HikeRequestComponentAction.SET_HIKEREQUEST, props<{ hikeRequest: HikeRequest[] }>());
export const saveHikeRequest = createAction(HikeRequestComponentAction.SAVE_HIKEREQUEST, props<{ hikeRequest: HikeRequest }>());
export const updateHikeRequest = createAction(HikeRequestComponentAction.UPDATE_HIKEREQUEST, props<{ hikeRequest: HikeRequest }>());
export const deleteHikeRequest = createAction(HikeRequestComponentAction.DELETE_HIKEREQUEST, props<{ hikeRequest: HikeRequest }>());