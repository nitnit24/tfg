import {config, appFetch } from './appFetch';


export const addSaleRoom = (idRoomType, saleRoomDate, freeRooms, onSuccess, onErrors) => {

    const date = saleRoomDate.getTime();

    appFetch('/dailyPanel/addSaleRoom', config('POST', {idRoomType, date, freeRooms}), 
    onSuccess, onErrors);
}

export const findSaleRoom = (roomTypeId, saleRoomDate, onSuccess, onErrors) => {

    const date = saleRoomDate.getTime();

    let path = `/dailyPanel/findSaleRoom?roomTypeId=${roomTypeId}`;

    path += `&date=${date}`;
   
    appFetch(path, config('GET'), onSuccess, onErrors);

}

export const uploadSaleRoomTariff = (price, tariffId, roomTypeId, saleRoomTariffDate, onSuccess, onErrors) => {
  
    const date = saleRoomTariffDate.getTime();

    appFetch('/dailyPanel/uploadSaleRoomTariff', config('POST', {price, tariffId, roomTypeId, date}), 
    onSuccess, onErrors);
}

export const findSaleRoomTariff = (tariffId, roomTypeId, saleRoomTariffDate, onSuccess, onErrors) => {
    
    const date = saleRoomTariffDate.getTime();
    
    let path = `/dailyPanel/findSaleRoomTariff?roomTypeId=${roomTypeId}`;

    path += tariffId ? `&tariffId=${tariffId}` : "";
    
    path += date ? `&date=${date}` : "";

    appFetch(path, config('GET'), onSuccess, onErrors);
}
