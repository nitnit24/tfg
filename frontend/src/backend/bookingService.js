import {config, appFetch } from './appFetch';


export const findFreeRooms = (startDate, endDate, people, rooms, onSuccess) => {
    console.log("FIND"),
    console.log("start" + startDate),
    console.log("end" + endDate),
    console.log("people" + people),
    console.log("rooms" + rooms)
    
    let path = `/booking/findFreeRooms?startDate=${startDate}`;

    path += endDate ? `&endDate=${endDate}` : "";
    
    path += people ? `&people=${people}` : "";
    
    path += rooms ? `&rooms=${rooms}` : "";
    
    console.log(path)
    appFetch(path, config('GET'), onSuccess);

}

export const findTariffsByFreeRoom = (startDate, endDate, roomTypeId, onSuccess) => {
    console.log("FIND"),
    console.log("start" + startDate),
    console.log("end" + endDate),
    console.log("roomTypeId" + roomTypeId)
    
    let path = `/booking/findTariffsByFreeRoom?startDate=${startDate}`;

    path += endDate ? `&endDate=${endDate}` : "";
    
    path += roomTypeId ? `&roomTypeId=${roomTypeId}` : "";
    
    console.log(path)
    appFetch(path, config('GET'), onSuccess);

}



export const findSaleRoomTariffsByFreeRoom = (startDate, endDate, roomTypeId, tariffId, onSuccess) => {
    console.log("FIND"),
    console.log("start" + startDate),
    console.log("end" + endDate),
    console.log("roomTypeId" + roomTypeId),
    console.log("tariffId" + tariffId)
    
    let path = `/booking/findSaleRoomTariffsByFreeRoom?startDate=${startDate}`;

    path += endDate ? `&endDate=${endDate}` : "";
    
    path += roomTypeId ? `&roomTypeId=${roomTypeId}` : "";

    path += tariffId ? `&tariffId=${tariffId}` : "";
    
    console.log(path)
    appFetch(path, config('GET'), onSuccess);

}