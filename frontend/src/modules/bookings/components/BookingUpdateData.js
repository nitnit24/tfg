import React from 'react';

import ClientForm from './ClientForm';
import BookingSummary from './BookingSummary';

const BookingUpdateData = ({history}) => (
    <div>
        <div className = "row">
            <div className= "col-6">
                <ClientForm history={history}/> 
            </div>
            <div className= "col-6">
                <BookingSummary history={history}/> 
            </div>
        </div>
    </div>
);


export default BookingUpdateData;