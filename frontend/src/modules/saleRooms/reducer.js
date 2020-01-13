import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    startDate: '',
    endDate:'',
    freeRoomTypes: null,
    rooms: [],
    summaryRooms: [],
    lastBooking:''
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

const rooms = (state = initialState.rooms, action) => {

    switch (action.type) {

        case actionTypes.ADD_ROOMS_COMPLETED:
            return [ ...state, action.room];
        
        case actionTypes.REMOVE_ROOMS_COMPLETED:{ 
                return state.filter(room => (room.saleRoomTariffs !== action.room.saleRoomTariffs))
        }

        case actionTypes.CLEAN_ROOMS_COMPLETED:{ 
            return []
        }

    default:
        return state;
    }

}

const summaryRooms = (state = initialState.summaryRooms, action) => {

    switch (action.type) {

        case actionTypes.ADD_SUMMARYROOMS_COMPLETED:{
            return [ ...state, action.summaryRoom];
        }

        case actionTypes.REMOVE_SUMMARYROOMS_COMPLETED:{    
            return state.filter(summaryRoom => (summaryRoom.tariff !== action.summaryRoom.tariff) || (summaryRoom.id !== action.summaryRoom.id))
        }

        case actionTypes.CLEAN_SUMMARYROOMS_COMPLETED:{
            return []
        }

    default:
        return state;
    }

}


const lastBooking = (state = initialState.lastBooking, action) => {

    switch (action.type) {

        case actionTypes.BOOKING_COMPLETED:
     
            return action.booking;

    default:
        return state;
    }

}


const reducer = combineReducers({
    startDate, 
    endDate, 
    freeRoomTypes,
    rooms,
    summaryRooms,
    lastBooking
});

export default reducer;