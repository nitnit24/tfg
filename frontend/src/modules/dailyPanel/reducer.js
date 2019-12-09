import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    date: ''
};

const date = (state = initialState.date, action) => {

    switch (action.type) {

        case actionTypes.ADD_DATE_COMPLETED:
     
            return action.date;

    default:
        return state;
    }

}


const reducer = combineReducers({
    date
});

export default reducer;