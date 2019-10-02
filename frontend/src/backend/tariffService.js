import {config, appFetch } from './appFetch';


export const addTariff = (tariff, onSuccess, onErrors) => 
    appFetch('/tariffs/addTariff', config('POST', tariff), 
    onSuccess, onErrors);