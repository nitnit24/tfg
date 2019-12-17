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
