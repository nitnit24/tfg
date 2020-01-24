 
import {config, appFetch } from './appFetch';


export const addRoomType = (roomType, onSuccess, onErrors) => {
    let path = `/roomTypes/addRoomType`;
    console.log(path)
    appFetch(path, config('POST',  roomType), 
    onSuccess, onErrors)};

export const findAllRoomTypes = (onSuccess,onErrors) => 
    appFetch('/roomTypes/roomTypes', config('GET'),
    onSuccess, onErrors);

 export const updateRoomType = (roomType, onSuccess, onErrors) =>
    appFetch(`/roomTypes/${roomType.id}`, config('PUT', roomType),
    onSuccess, onErrors);

export const removeRoomType = (roomType, onSuccess, onErrors) =>{
    appFetch(`/roomTypes/${roomType.id}`, config('DELETE'), 
    onSuccess, onErrors);}

export const findRoomTypeById = (id, onSuccess, onErrors) =>{
    appFetch(`/roomTypes/${id}`, config('GET'), 
    onSuccess, onErrors);}