import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    bookingSearch: null, 
    booking: null
    
};


const bookingSearch = (state = initialState.bookingSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_BOOKINGS_COMPLETED:
            return action.bookingSearch;

        case actionTypes.CLEAR_BOOKING_SEARCH:
            return initialState.bookingSearch;

        default:
            return state;

    }

}

const booking = (state = initialState.booking, action) => {

    switch (action.type) {

        case actionTypes.FIND_BOOKING_BY_LOCATOR_COMPLETED:
            return action.booking;

        case actionTypes.CLEAR_BOOKING:
            return initialState.booking;

        default:
            return state;

    }

}


const reducer = combineReducers({
    bookingSearch,
    booking
});

export default reducer;