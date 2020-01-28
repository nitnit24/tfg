import React from 'react';

import ClientUpdateBookingForm from './ClientUpdateBookingForm';
import BookingUpdateSummary from './BookingUpdateSummary';

const BookingUpdateData = ({history}) => (
    <div>
        <div className = "row">
            <div className= "col-6">
                <ClientUpdateBookingForm history={history}/> 
            </div>
            <div className= "col-6">
                <BookingUpdateSummary history={history}/> 
            </div>
        </div>
    </div>
);


export default BookingUpdateData;