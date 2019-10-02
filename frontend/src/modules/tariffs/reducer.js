import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    tariffs: null
};

const tariff = (state = initialState, action) => {

    switch (action.type) {

        default:
            return state;

    }

}

const reducer = combineReducers({
    tariff
});

export default reducer;


