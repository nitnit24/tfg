import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    roomTypes: [],
    roomType: {
        id: '',
        name: '',
        capacity: '',
        minPrice: '',
        maxPrice: ''

    }
};

const roomTypes = (state = initialState.roomTypes, action) => {

    switch (action.type) {

        case actionTypes.FIND_ALL_ROOM_TYPES_COMPLETED:
     
            return action.roomTypes;

    default:
        return state;
    }

}

const roomType = (state = initialState.roomType, action) => {

    switch (action.type) {

        case actionTypes.FIND_ROOM_TYPE_BY_ID_COMPLETED:
        
            return action.roomType;

        default:
            return state;

    }

}

const reducer = combineReducers({
    roomTypes,
    roomType
});

export default reducer;