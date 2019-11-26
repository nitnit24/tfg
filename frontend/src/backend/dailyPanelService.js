import {config, appFetch } from './appFetch';


export const addSaleRoom = (idRoomType, date, freeRooms, onSuccess, onErrors) => {
    console.log("back"),
    console.log(idRoomType),
    console.log(date),
    console.log(freeRooms)
    appFetch('/dailyPanel/addSaleRoom', config('POST', {idRoomType, date, freeRooms}), 
    onSuccess, onErrors);
}


export const findSaleRoom = (roomTypeId, date, onSuccess, onErrors) => {
    console.log("backFind"),
    console.log(roomTypeId),
    console.log(date)
    appFetch('/dailyPanel/findSaleRoom', config('GET', {roomTypeId, date}), 
    onSuccess, onErrors);
}

export const uploadSaleRoomTariff = (price, tariffId, roomTypeId, date, onSuccess, onErrors) => {
    console.log("back"),
    console.log(roomTypeId),
    console.log(tariffId),
    console.log(date),
    console.log(price)
    appFetch('/dailyPanel/uploadSaleRoomTariff', config('POST', {price, tariffId, roomTypeId, date}), 
    onSuccess, onErrors);
}

export const findSaleRoomTariff = (tariffId, roomTypeId, date, onSuccess, onErrors) => {
    console.log("back"),
    console.log(roomTypeId),
    console.log(tariffId),
    console.log(date)
    appFetch('/dailyPanel/findSaleRoomTariff', config('GET', {tariffId, roomTypeId, date}), 
    onSuccess, onErrors);
}