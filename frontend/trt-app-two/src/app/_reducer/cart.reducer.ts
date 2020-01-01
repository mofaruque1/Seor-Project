import { Cart } from "../_models/cart.model";
import * as CartActions from "../_actions/cart.actions";


class Helper {

    public static getCartItems(): any[] {
        return JSON.parse(localStorage.getItem("order")) || [];
    }

    public static getTotalPrice(): number {
        let temp = this.getCartItems();
        let totalPrice = 0;
        temp.forEach(element => {
            totalPrice += element.price;
        });
        return totalPrice;
    }

    public static getShippingCost(): number {
        let shippingCost = 100; //Could change in future
        return shippingCost;
    }

    public static getNoOfItems(): number {
        if (JSON.parse(localStorage.getItem("order"))) {
            return JSON.parse(localStorage.getItem("order")).length;
        } else {
            return 0;
        }

    }

    public static addToCart(state: Cart, item: any): Cart {
        state.noOfItem++;
        state.totalPrice += item.price;
        state.totalCostWithShipping = state.totalPrice + state.shippingCost;
        state.order.push(item);
        localStorage.setItem('order', JSON.stringify(state.order));
        return state;

    }

    public static removeFromCart(state: Cart, index: number): Cart {
        state.noOfItem--;
        state.totalPrice -= state.order[index].price;
        state.totalCostWithShipping = state.totalPrice + state.shippingCost;
        let newOrder: any[] = [];
        state.order.forEach((element, i) => {
            if (index != i) {
                newOrder.push(element);
            }
        });
        state.order = newOrder;
        localStorage.setItem('order', JSON.stringify(state.order));
        return state;
    }
}

const initialState: Cart = {
    noOfItem: Helper.getNoOfItems(),
    totalPrice: Helper.getTotalPrice(),
    shippingCost: Helper.getShippingCost(),
    totalCostWithShipping : Helper.getTotalPrice() + Helper.getShippingCost(),
    order: Helper.getCartItems()
}

export function cartReducer(state: Cart = initialState, action: CartActions.Actions) {
    console.log(action.type);
    console.log("Previous State : ");
    console.log(state);
    
    
    switch (action.type) {
        case CartActions.ADD_TO_CART:
            return Helper.addToCart(state, action.payload);
        case CartActions.REMOVE_FROM_CART:
            return Helper.removeFromCart(state, action.payload);
        default:
            return state;
    }
}