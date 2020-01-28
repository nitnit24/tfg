import React from 'react';
import {connect} from 'react-redux';
import {Route, Switch, withRouter} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword} from '../../users';
import users from '../../users';
import {Tariff, TariffUpdateForm} from '../../tariffs';
import {RoomType, RoomTypeUpdateForm} from '../../roomTypes';
import {SaleRoom, BookingData, BookingCompleted} from '../../saleRooms';
import {BookingsFind,  BookingDetails, BookingFindForm, BookingDetailsClient, BookingUpdateData} from '../../bookings';
import {DailyPanel} from '../../dailyPanel';

const Body = ({user}) => (

    <div className="container">
        <br/>
        <Route path="/" component={AppGlobalComponents}/>
        <Switch>
            <Route exact path="/" component={Home}/>
            {user && <Route exact path="/tariffs/tariff-management" component={Tariff}/>}
            {user && <Route exact path="/tariffs/tariff-update" component={TariffUpdateForm}/>}
            {user && <Route exact path="/roomTypes/roomType-management" component={RoomType}/>}
            {user && <Route exact path="/roomTypes/roomType-update" component={RoomTypeUpdateForm}/>}
            {user && <Route exact path="/dailyPanel/dailyPanel-management" component={DailyPanel}/>}
            {user && <Route exact path="/booking/bookings" component={BookingsFind}/>}
            {user && <Route exact path="/users/update-profile" component={UpdateProfile}/>}
            {user && <Route exact path="/users/change-password" component={ChangePassword}/>}
            {!user && <Route exact path="/users/login" component={Login}/>}
            {!user && <Route exact path="/users/signup" component={SignUp}/>}
            <Route exact path="/saleRooms/find-saleRooms" component={SaleRoom}/>
            <Route exact path="/booking/client-form" component={BookingData}/>
            <Route exact path="/booking/booking-completed" component={BookingCompleted}/>
            <Route exact path="/booking/booking-find" component={BookingFindForm}/>
            <Route exact path="/booking/booking-details/:locator" component={BookingDetails}/>
            <Route exact path="/booking/booking-details-client" component={BookingDetailsClient}/>
            <Route exact path="/booking/booking-update-form" component={ BookingUpdateData}/>
           
        </Switch>
    </div>

);

const mapStateToProps = (state, ownProps) => ({
    user: users.selectors.getUser(state)
});

export default withRouter(connect(mapStateToProps)(Body));
