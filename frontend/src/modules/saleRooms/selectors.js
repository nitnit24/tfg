const getModuleState = state => state.saleRooms;

export const getStartDate = state =>
    getModuleState(state).startDate;

export const getEndDate = state =>
    getModuleState(state).endDate;

export const getFreeRoomTypes = state =>
    getModuleState(state).freeRoomTypes;

export const getTariffsByFreeRoom = state =>
    getModuleState(state).tariffsByFreeRoom;

export const getRooms = state =>
    getModuleState(state).rooms;

export const getSummaryRooms = state =>
    getModuleState(state).summaryRooms;

export const getLastBooking= state =>
    getModuleState(state).lastBooking;