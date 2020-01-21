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


const uploadFreeRoomsCompleted = saleRoom => ({
    type: actionTypes.UPLOAD_FREEROOMS_COMPLETED,
    saleRoom
});
      
export const uploadFreeRooms = (roomTypeId, date, freeRooms) => (dispatch) => {
    backend.dailyPanelService.addSaleRoom (roomTypeId, date, freeRooms, 
        saleRoom => {console.log(saleRoom) , dispatch(uploadFreeRoomsCompleted(saleRoom))});
}

const uploadTariffPriceCompleted = (saleRoomTariff, roomTypeId, day) => ({
    type: actionTypes.UPLOAD_TARIFFPRICE_COMPLETED,
    data : {saleRoomTariff, roomTypeId,day}
});
      
export const uploadTariffPrice = (price, tariffId, roomTypeId, day) => (dispatch) => {
    backend.dailyPanelService.uploadSaleRoomTariff (price, tariffId, roomTypeId, day, 
        saleRoomTariff => {console.log("SaleRoomTariff  " + saleRoomTariff) ,
                            console.log("roomTypeId" + roomTypeId),
                            console.log("day" + day )
                             dispatch(uploadTariffPriceCompleted(saleRoomTariff, roomTypeId, day))}
        );
}