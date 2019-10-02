const getModuleState = state => state.tariffs;

export const getTariff = state =>
    getModuleState(state).tariff;