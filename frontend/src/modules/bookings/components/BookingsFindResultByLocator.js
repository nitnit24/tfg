import React from 'react';
import {connect} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';
import * as actions from '../actions';
import {Pager} from '../../common';
import Bookings from './Bookings';

const BookingsFindResultByLocator = ({bookingSearch,previousFindBookingsResultByLocatorPage, nextFindBookingsResultByLocatorPage}) => {

    if (!bookingSearch) {
        return null;
    }

    if (bookingSearch.result.items.length === 0) {
        return (
            <div className="alert alert-danger" role="alert">
                <FormattedMessage id='project.bookings.FindBookingsResult.noBookingsFound'/>
            </div>
        );
    }
    
    return (

        <div>
            <Bookings bookings={bookingSearch.result.items}/>
            <Pager 
                back={{
                    enabled: bookingSearch.criteria.page >= 1,
                    onClick: () => previousFindBookingsResultByLocatorPage(bookingSearch.criteria)}}
                next={{
                    enabled: bookingSearch.result.existMoreItems,
                    onClick: () => nextFindBookingsResultByLocatorPage(bookingSearch.criteria)}}/>
        </div>

    );

}

const mapStateToProps = (state) => ({
    bookingSearch: selectors.getBookingSearch(state)
});

const mapDispatchToProps = {
    previousFindBookingsResultByLocatorPage: actions.previousFindBookingsResultByLocatorPage,
    nextFindBookingsResultByLocatorPage: actions.nextFindBookingsResultByLocatorPage
}

export default connect(mapStateToProps, mapDispatchToProps)(BookingsFindResultByLocator);