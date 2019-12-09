import {config, appFetch } from './appFetch';


export const addSaleRoom = (idRoomType, date, freeRooms, onSuccess, onErrors) => {
    console.log("ADD"),
    console.log("addID" + idRoomType),
    console.log("add" + date)
    console.log("add" +freeRooms)
    appFetch('/dailyPanel/addSaleRoom', config('POST', {idRoomType, date, freeRooms}), 
    onSuccess, onErrors);
}

export const findSaleRoom = (roomTypeId, date, onSuccess, onErrors) => {
    console.log("FIND"),
    console.log("ID" + roomTypeId),
    console.log(date)
    
    let path = `/dailyPanel/findSaleRoom?roomTypeId=${roomTypeId}`;

    path += date ? `&date=${date}` : "";
    
    appFetch(path, config('GET'), onSuccess, onErrors);

}

export const uploadSaleRoomTariff = (price, tariffId, roomTypeId, date, onSuccess, onErrors) => {
    // console.log("ADD"),
    // console.log("addID" + roomTypeId),
    // console.log("add" + date)
    // console.log("add" + price)
    appFetch('/dailyPanel/uploadSaleRoomTariff', config('POST', {price, tariffId, roomTypeId, date}), 
    onSuccess, onErrors);
}

export const findSaleRoomTariff = (tariffId, roomTypeId, date, onSuccess, onErrors) => {
    // console.log("backFind"),
    // console.log("IDTariff"+ tariffId)
    // console.log("IDRoom" + roomTypeId),
    // console.log(date)
    
    let path = `/dailyPanel/findSaleRoomTariff?roomTypeId=${roomTypeId}`;

    path += tariffId ? `&tariffId=${tariffId}` : "";
    
    path += date ? `&date=${date}` : "";

    appFetch(path, config('GET'), onSuccess, onErrors);
}
