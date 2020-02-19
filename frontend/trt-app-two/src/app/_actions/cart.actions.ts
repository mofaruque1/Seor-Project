import { Action } from '@ngrx/store'
export const ADD_TO_CART = '[CART] Add'
export const REMOVE_FROM_CART = '[CART] Remove'
export const REMOVE_ALL_FROM_CART = '[CART] Remove all'


export class AddToCart implements Action {
    readonly type = ADD_TO_CART;
    constructor(public payload:any){} 
}

export class RemoveFromCart implements Action {
    readonly type = REMOVE_FROM_CART;
    constructor(public payload:number){} 
}

export class RemoveAllFromCart implements Action {
    readonly type = REMOVE_ALL_FROM_CART;
}

export type Actions = AddToCart | RemoveFromCart | RemoveAllFromCart