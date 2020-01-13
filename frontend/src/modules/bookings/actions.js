import * as actionTypes from './actionTypes';
import * as selectors from './selectors';
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

const clearBookingSearch = () => ({
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
        booking =>  dispatch(findBookingByLocatorCompleted (booking))
    );
}

export const clearBooking = () => ({
    type: actionTypes.CLEAR_BOOKING
});