import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import tariffs from '../modules/tariffs';
import roomTypes from '../modules/roomTypes';
import dailyPanel from '../modules/dailyPanel';
import saleRooms from '../modules/saleRooms';
import bookings from '../modules/bookings';

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    tariffs: tariffs.reducer,
    roomTypes: roomTypes.reducer,
    dailyPanel: dailyPanel.reducer,
    saleRooms: saleRooms.reducer,
    bookings: bookings.reducer
});

export default rootReducer;
