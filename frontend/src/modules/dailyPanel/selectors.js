const getModuleState = state => state.dailyPanel;

export const getDate = state =>
    getModuleState(state).date;

export const getRoomTables = state =>
    getModuleState(state).roomTables;
