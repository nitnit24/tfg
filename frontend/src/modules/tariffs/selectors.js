const getModuleState = state => state.tariffs;

export const getTariffs = state =>
    getModuleState(state).tariffs;