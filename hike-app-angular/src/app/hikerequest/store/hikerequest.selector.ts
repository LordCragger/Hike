import { createFeatureSelector, createSelector } from '@ngrx/store';
import { HikeRequestComponentState } from '../hikerequest.model';

export const selectHikeRequestComponentState = createFeatureSelector<HikeRequestComponentState>('hikeRequestComponent');
export const selectHikeRequest = createSelector(selectHikeRequestComponentState, (state => state && state.hikeRequest));