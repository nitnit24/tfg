import * as actionTypes from './actionTypes';
import backend from '../../backend';

const addDateCompleted = date => ({
    type: actionTypes.ADD_DATE_COMPLETED,
    date
});
      

export const addDate = (date) => (dispatch) => {
    dispatch(addDateCompleted(date));
}

const findRoomTablesCompleted = roomTables => ({
    type: actionTypes.FIND_ROOMTABLES_COMPLETED,
    roomTables
});
      
export const findRoomTables = (date) => (dispatch) => {
    dispatch(clearRoomTables());
    backend.dailyPanelService.findDailyPanel(date, 
        tableRooms => dispatch(findRoomTablesCompleted(tableRooms)));
}

const clearRoomTables = () => ({
    type: actionTypes.CLEAR_ROOM_TABLES
});