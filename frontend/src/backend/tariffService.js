import {config, appFetch } from './appFetch';


export const addTariff = (tariff, onSuccess, onErrors) => 
    appFetch('/tariffs/addTariff', config('POST', tariff), 
    onSuccess, onErrors);

export const findAllTariffs = (onSuccess,onErrors) => 
    appFetch('/tariffs/tariffs', config('GET'),
    onSuccess, onErrors);

export const updateTariff = (tariff, onSuccess, onErrors) =>
    appFetch(`/tariffs/${tariff.id}`, config('PUT', tariff),
    onSuccess, onErrors);

export const removeTariff = (tariff, onSuccess, onErrors) =>{
    appFetch(`/tariffs/${tariff.id}`, config('DELETE'), 
    onSuccess, onErrors);}

export const findTariffById = (id, onSuccess, onErrors) =>{
    appFetch(`/tariffs/${id}`, config('GET'), 
    onSuccess, onErrors);}