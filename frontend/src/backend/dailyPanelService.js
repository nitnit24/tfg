import {config, appFetch } from './appFetch';


export const addSaleRoom = (idRoomType, date, freeRooms, onSuccess, onErrors) => {

    //const date = saleRoomDate.getTime();

    appFetch('/dailyPanel/addSaleRoom', config('POST', {idRoomType, date, freeRooms}), 
    onSuccess, onErrors);
}


export const uploadSaleRoomTariff = (price, tariffId, roomTypeId, date, onSuccess, onErrors) => {
  
    //const date = saleRoomTariffDate.getTime();

    appFetch('/dailyPanel/uploadSaleRoomTariff', config('POST', {price, tariffId, roomTypeId, date}), 
    onSuccess, onErrors);
}


export const findDailyPanel = (date, onSuccess, onErrors) => {

    let path = `/dailyPanel/findDailyPanel?date=${date}`;

    appFetch(path, config('GET'), onSuccess, onErrors);
}
