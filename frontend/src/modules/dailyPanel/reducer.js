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
        
        case actionTypes.UPLOAD_FREEROOMS_COMPLETED:

            return (
                state.map(
                    (roomTable) => roomTable.roomTypeId === action.saleRoom.roomTypeId ? 
                        {...roomTable, roomTableDays: roomTable.roomTableDays.map (
                            (roomTableDay) => roomTableDay.day === action.saleRoom.date ?
                            {...roomTableDay, freeRooms: action.saleRoom.freeRooms} : roomTableDay
                    )
                    } : roomTable
             )
        )

        case actionTypes.UPLOAD_TARIFFPRICE_COMPLETED:

        return (
            state.map(
                (roomTable) => roomTable.roomTypeId === action.data.roomTypeId ? 
                     {...roomTable, roomTableDays: roomTable.roomTableDays.map (
                         (roomTableDay) => roomTableDay.day === action.data.day ?
                            {...roomTableDay, roomTableTariffs: roomTableDay.roomTableTariffs.map(
                                (roomTableTariff) => roomTableTariff.tariffId === action.data.saleRoomTariff.tariffId ?
                                    {...roomTableTariff, price : action.data.saleRoomTariff.price} : roomTableTariff
                            )
                            }: roomTableDay
                  )
                } : roomTable
              
         )
    )


    default:
        return state;
    }

}


const reducer = combineReducers({
    date,
    roomTables
});

export default reducer;