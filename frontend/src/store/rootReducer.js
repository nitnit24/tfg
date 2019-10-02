import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import tariffs from '../modules/tariffs';

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    tariffs: tariffs.reducer
});

export default rootReducer;
