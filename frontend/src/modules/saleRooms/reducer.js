import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';
import { rootCertificates } from 'tls';
import { removeRoomType } from '../../backend/roomTypeService';
import { log } from 'util';

const initialState = {
    startDate: '',
    endDate:'',
    freeRoomTypes: [],
    tariffsByFreeRoom: [],
    rooms: []
};

const startDate = (state = initialState.startDate, action) => {

    switch (action.type) {

        case actionTypes.ADD_STARTDATE_COMPLETED:
     
            return action.startDate;

    default:
        return state;
    }

}

const endDate = (state = initialState.endDate, action) => {

    switch (action.type) {

        case actionTypes.ADD_ENDDATE_COMPLETED:
     
            return action.endDate;

    default:
        return state;
    }

}


const freeRoomTypes = (state = initialState.freeRoomTypes, action) => {

    switch (action.type) {

        case actionTypes.ADD_FREEROOMTYPES_COMPLETED:
     
            return action.freeRoomTypes;

        case actionTypes.CLEAN_FREEROOMTYPES_COMPLETED:
     
            return [];

    default:
        return state;
    }

}

const tariffsByFreeRoom = (state = initialState.tariffsByFreeRoom, action) => {

    switch (action.type) {

        case actionTypes.ADD_TARIFFSBYFREEROOM_COMPLETED:
     
            return action.tariffsByFreeRoom;

    default:
        return state;
    }

}

const rooms = (state = initialState.rooms, action) => {

    switch (action.type) {

        case actionTypes.ADD_ROOM_COMPLETED:{
            return [ ...state, action.room];
        }

        case actionTypes.REMOVE_ROOM_COMPLETED:{    
            return state.filter(room => (room.tariff !== action.room.tariff) || (room.id !== action.room.id))
        }

    default:
        return state;
    }

}

const reducer = combineReducers({
    startDate, 
    endDate, 
    freeRoomTypes,
    tariffsByFreeRoom,
    rooms
});

export default reducer;