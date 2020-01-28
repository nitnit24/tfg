const getModuleState = state => state.bookings;

export const getBookingSearch = state =>
    getModuleState(state).bookingSearch;

export const getBooking = state =>
    getModuleState(state).booking;

export const getClientData = state =>
    getModuleState(state).clientData;