import {config, appFetch } from './appFetch';


export const findFreeRooms = (startDate, endDate, people, rooms, onSuccess) => {
    // console.log("FIND"),
    // console.log("start" + startDate),
    // console.log("end" + endDate),
    // console.log("people" + people),
    // console.log("rooms" + rooms)
    
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

    
    let path = `/booking/findSaleRoomTariffsByFreeRoom?startDate=${startDate}`;

    path += endDate ? `&endDate=${endDate}` : "";
    
    path += roomTypeId ? `&roomTypeId=${roomTypeId}` : "";

    path += tariffId ? `&tariffId=${tariffId}` : "";
    
    console.log(path)
    appFetch(path, config('GET'), onSuccess);

}

export const makeBooking = (bookingRoomSummarys, startDate, endDate, name, surname, phone, email, petition, onSuccess, onErrors) => {

    let path = `/booking/makeBooking?startDate=${startDate}`;

    path += endDate ? `&endDate=${endDate}` : "";

    appFetch(path, config('POST', {bookingRoomSummarys, startDate, endDate, name, surname, phone, email, petition}), 
    onSuccess, onErrors);
}


export const findBookingByLocator = (locator, onSuccess, onErrors) =>{
    console.log(locator);
    appFetch(`/booking/${locator}`, config('GET'), 
    onSuccess, onErrors);}


export const findBookingByLocatorAndKey = (locator, key , onSuccess, onErrors) =>{
    appFetch(`/booking/${locator}/find?key=${key}`, config('GET'), 
     onSuccess, onErrors);}


export const cancelBooking = (locator, key , onSuccess, onErrors) =>{
    let path = `/booking/${locator}/cancel?key=${key}`;
    appFetch(path, config('PUT'), onSuccess, onErrors);}


export const findBookings = ({dateType, minDate, maxDate, keywords, page}, 
    onSuccess) => {
    
    let path = `/booking/bookings?page=${page}`;
    
    path += dateType ? `&dateType=${dateType}` : "";
    path += minDate ? `&minDate=${minDate}` : "";
    path += maxDate ? `&maxDate=${maxDate}` : "";
    path += keywords.length > 0 ? `&keywords=${keywords}` : "";
    
    appFetch(path, config('GET'), onSuccess);
    
}   