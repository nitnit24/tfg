import * as actionTypes from './actionTypes';
import backend from '../../backend';


export const addRoomType = (roomType, onErrors) => (dispatch) => {

    backend.roomTypeService.addRoomType(roomType,
        () =>dispatch(findAllRoomTypes()),
        onErrors)
}

export const removeRoomType = (roomType, onErrors) => (dispatch) => {
   
    backend.roomTypeService.removeRoomType(roomType,
        () => dispatch(findAllRoomTypes()),
        onErrors)
}

export const updateRoomType = (roomType, onSuccess, onErrors) => (dispatch) => {
   
    backend.roomTypeService.updateRoomType(roomType,
        () => {
           // dispatch(findAllRoomTypes());
            onSuccess();
        },
        onErrors)
}

const findAllRoomTypesCompleted = roomTypes => ({
        type: actionTypes.FIND_ALL_ROOM_TYPES_COMPLETED,
        roomTypes
    }); 


export const findAllRoomTypes = () => (dispatch) => {
        
        backend.roomTypeService.findAllRoomTypes(
            roomTypes => dispatch(findAllRoomTypesCompleted(roomTypes))
        );
    }

const findRoomTypeByIdCompleted = roomType => ({
    type: actionTypes.FIND_ROOM_TYPE_BY_ID_COMPLETED,
    roomType
});
        


export const findRoomTypeById = (id, onSuccess) => (dispatch) => {
    backend.roomTypeService.findRoomTypeById(id,
        roomType => {
            dispatch(findRoomTypeByIdCompleted(roomType));
            onSuccess();
        })
}