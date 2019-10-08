import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    tariffs: null
};

const tariffs = (state = initialState.tariffs, action) => {

    switch (action.type) {

    case actionTypes.FIND_ALL_TARIFFS_COMPLETED:
        return action.tariffs;

    default:
        return state;

    }

}

const reducer = combineReducers({
    tariffs
});

export default reducer;


