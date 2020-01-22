import * as actionTypes from './actionTypes';


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



const addFreeRoomTypesCompleted = freeRoomTypes => ({
    type: actionTypes.ADD_FREEROOMTYPES_COMPLETED,
    freeRoomTypes
});


export const addFreeRoomTypes = (freeRoomTypes) => (dispatch) => {
    dispatch(addFreeRoomTypesCompleted(freeRoomTypes));
}

const cleanFreeRoomTypesCompleted = () => ({
    type: actionTypes.CLEAN_FREEROOMTYPES_COMPLETED,
});


export const cleanFreeRoomTypes = () => (dispatch) => {
    dispatch(cleanFreeRoomTypesCompleted());
}


const addRoomsCompleted = room => ({
    type: actionTypes.ADD_ROOMS_COMPLETED,
    room
});
      

export const addRooms = (room) => (dispatch) => {
    dispatch(addRoomsCompleted(room));
}

const removeRoomsCompleted = room => ({
    type: actionTypes.REMOVE_ROOMS_COMPLETED,
    room
});
      

export const removeRooms = (room) => (dispatch) => {
    dispatch(removeRoomsCompleted(room));
}

const cleanRoomsCompleted = () => ({
    type: actionTypes.CLEAN_ROOMS_COMPLETED,
});


export const cleanRooms = () => (dispatch) => {
    dispatch(cleanRoomsCompleted());
}

const addSummaryRoomsCompleted = summaryRoom => ({
    type: actionTypes.ADD_SUMMARYROOMS_COMPLETED,
    summaryRoom
});
      

export const addSummaryRooms = (summaryRoom) => (dispatch) => {
    dispatch(addSummaryRoomsCompleted(summaryRoom));
}

const removeSummaryRoomsCompleted = summaryRoom => ({
    type: actionTypes.REMOVE_SUMMARYROOMS_COMPLETED,
    summaryRoom
});
      

export const removeSummaryRooms= (summaryRoom) => (dispatch) => {
    dispatch(removeSummaryRoomsCompleted(summaryRoom));
}

const cleanSummaryRoomsCompleted = () => ({
    type: actionTypes.CLEAN_SUMMARYROOMS_COMPLETED,
});


export const cleanSummaryRooms = () => (dispatch) => {
    dispatch(cleanSummaryRoomsCompleted());
}

const bookingCompleted = booking => ({
    type: actionTypes.BOOKING_COMPLETED,
    booking
});
      

export const booking= (booking) => (dispatch) => {
    dispatch(bookingCompleted(booking));
}