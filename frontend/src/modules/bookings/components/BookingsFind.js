import React from 'react';
import {FormattedMessage} from 'react-intl';

import BookingsFindForm from './BookingsFindForm';
import {Link} from 'react-router-dom';
import BookingsFindResult from './BookingsFindResult';

const BookingsFind = ({history}) => (
    <div>
        <BookingsFindForm history={history}/>
        &nbsp;
       <BookingsFindResult history={history}/>
    </div>
);


export default BookingsFind;