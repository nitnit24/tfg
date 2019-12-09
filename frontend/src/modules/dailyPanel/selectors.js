const getModuleState = state => state.dailyPanel;

export const getDate = state =>
    getModuleState(state).date;
