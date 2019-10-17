import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    tariffs: [],
    tariff: {
        id: '',
        name: '',
        code: ''

    }
};

const tariffs = (state = initialState.tariffs, action) => {

    switch (action.type) {

    case actionTypes.FIND_ALL_TARIFFS_COMPLETED:
     
        return action.tariffs;

    default:
        return state;
    }

}

const tariff = (state = initialState.tariff, action) => {

    switch (action.type) {

        case actionTypes.FIND_TARIFF_BY_ID_COMPLETED:
        
            return action.tariff;

        default:
            return state;

    }

}

const reducer = combineReducers({
    tariffs,
    tariff
});

export default reducer;


