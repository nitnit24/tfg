const getModuleState = state => state.roomTypes;

export const getRoomTypes = state =>
    getModuleState(state).roomTypes;

export const getRoomType = state =>
    getModuleState(state).roomType;