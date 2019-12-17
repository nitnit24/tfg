const getModuleState = state => state.saleRooms;

export const getFreeRoomTypes = state =>
    getModuleState(state).freeRoomTypes;
