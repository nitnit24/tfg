import * as actionTypes from './actionTypes';
import backend from '../../backend';
import { removeRoomType } from '../../backend/roomTypeService';


const addStartDateCompleted = startDate => ({
    type: actionTypes.ADD_STARTDATE_COMPLETED,
    startDate
});
      

export const addStartDate = (startDate) => (dispatch) => {
    dispatch(addStartDateCompleted(startDate));
}


const addEndDateCompleted = endDate => ({
    type: actionTypes.ADD_ENDDATE_COMPLETED,
    endDate
});
      

export const addEndDate = (endDate) => (dispatch) => {
    dispatch(addEndDateCompleted(endDate));
}

const addQuantityCompleted = quantity => ({
    type: actionTypes.ADD_QUANTITY_COMPLETED,
    quantity
});
      

export const addQuantity = (quantity) => (dispatch) => {
    dispatch(addQuantityCompleted(quantity));
}

const addFreeRoomTypesCompleted = freeRoomTypes => ({
    type: actionTypes.ADD_FREEROOMTYPES_COMPLETED,
    freeRoomTypes
});


export const addFreeRoomTypes = (freeRoomTypes) => (dispatch) => {
    dispatch(addFreeRoomTypesCompleted(freeRoomTypes));
}

const addTariffsByFreeRoomCompleted = tariffsByFreeRoom => ({
    type: actionTypes.ADD_TARIFFSBYFREEROOM_COMPLETED,
    tariffsByFreeRoom
});
      

export const addTariffsByFreeRoom = (tariffsByfreeRoom) => (dispatch) => {
    dispatch(addTariffsByFreeRoomCompleted(tariffsByfreeRoom));
}


const addRoomCompleted = room => ({
    type: actionTypes.ADD_ROOM_COMPLETED,
    room
});
      

export const addRoom = (room) => (dispatch) => {
    dispatch(addRoomCompleted(room));
}

const addRemoveCompleted = room => ({
    type: actionTypes.REMOVE_ROOM_COMPLETED,
    room
});
      

export const removeRoom = (room) => (dispatch) => {
    console.log(" actions" + room.id + room.name)
    dispatch(addRemoveCompleted(room));
}