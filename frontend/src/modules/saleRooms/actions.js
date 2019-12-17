import * as actionTypes from './actionTypes';
import backend from '../../backend';


const addFreeRoomTypesCompleted = freeRoomTypes => ({
    type: actionTypes.ADD_FREEROOMTYPES_COMPLETED,
    freeRoomTypes
});
      

export const addFreeRoomTypes = (freeRoomTypes) => (dispatch) => {
    dispatch(addFreeRoomTypesCompleted(freeRoomTypes));
}

