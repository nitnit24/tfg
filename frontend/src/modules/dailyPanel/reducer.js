import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    date: null,
    roomTables: null 
};

const date = (state = initialState.date, action) => {

    switch (action.type) {

        case actionTypes.ADD_DATE_COMPLETED:
     
            return action.date;

    default:
        return state;
    }

}


const roomTables = (state = initialState.roomTables, action) => {

    switch (action.type) {

        case actionTypes.FIND_ROOMTABLES_COMPLETED:
     
            return action.roomTables;
         
        case actionTypes.CLEAR_ROOM_TABLES:
     
            return null;

    default:
        return state;
    }

}


const reducer = combineReducers({
    date,
    roomTables
});

export default reducer;