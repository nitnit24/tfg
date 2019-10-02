import backend from '../../backend';

export const addTariff = (tariff, onSuccess, 
    onErrors) => backend.tariffService.addTariff(tariff,onSuccess,onErrors)


