import * as actionTypes from './actionTypes';
import backend from '../../backend';

const findBookingsCompleted = bookingSearch => ({
    type: actionTypes.FIND_BOOKINGS_COMPLETED,
    bookingSearch
});

export const findBookings = criteria => dispatch => {
    dispatch(clearBookingSearch());
    backend.bookingService.findBookings(criteria,
        result => dispatch(findBookingsCompleted({criteria, result})));

}

export const clearBookingSearch = () => ({
    type: actionTypes.CLEAR_BOOKING_SEARCH
});

export const previousFindBookingsResultPage = criteria =>
    findBookings({...criteria, page: criteria.page-1});

export const nextFindBookingsResultPage = criteria =>
    findBookings({...criteria, page: criteria.page+1});


const findBookingByLocatorCompleted = booking => ({
    type: actionTypes.FIND_BOOKING_BY_LOCATOR_COMPLETED,
    booking
});
        

export const findBookingByLocator = locator => dispatch => {
    backend.bookingService.findBookingByLocator(locator, 
        booking =>  dispatch(findBookingByLocatorCompleted(booking))
    );
}

export const clearBooking = () => ({
    type: actionTypes.CLEAR_BOOKING
});

const bookingCompleted = booking => ({
    type: actionTypes.FIND_BOOKING_COMPLETED,
    booking
});
      

export const booking= (booking) => (dispatch) => {
    dispatch(bookingCompleted(booking));
}

const addClientDataCompleted = clientData => ({
    type: actionTypes.ADD_CLIENTDATA_COMPLETED,
    clientData
});
      

export const addClientData= (clientData) => (dispatch) => {
    dispatch(addClientDataCompleted(clientData));
}

const updateBookingCompleted = booking => ({
    type: actionTypes.UPDATE_BOOKING_COMPLETED,
    booking
});
      

export const updateBooking= (booking) => (dispatch) => {
    dispatch(updateBookingCompleted(booking));
}


const findBookingsByLocatorCompleted = bookingSearch => ({
    type: actionTypes.FIND_BOOKINGS_BY_LOCATOR_COMPLETED,
    bookingSearch
});


export const findBookingsByLocator = locator => dispatch => {
    dispatch(clearBookingSearch());
    let page = 0;
    let criteria = {locator,page};
    let items = [];
    let existMoreItems= false;
    let result = {items,existMoreItems}
    backend.bookingService.findBookingByLocator(locator, 
        booking =>{
            items.push(booking)
            dispatch(findBookingsByLocatorCompleted({criteria, result}))
        }
    );
}