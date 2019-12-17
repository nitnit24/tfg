import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    freeRoomTypes: []
};

const freeRoomTypes = (state = initialState.freeRoomTypes, action) => {

    switch (action.type) {

        case actionTypes.ADD_FREEROOMTYPES_COMPLETED:
     
            return action.freeRoomTypes;

    default:
        return state;
    }

}


const reducer = combineReducers({
    freeRoomTypes
});

export default reducer;