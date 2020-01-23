import {config, appFetch } from './appFetch';


export const findFreeRooms = (sDate, eDate, people, rooms, onSuccess) => {
    console.log(sDate)
    const startDate = (new Date(sDate)).getTime();
    console.log(startDate)
    const endDate = (new Date(eDate)).getTime()
    
    let path = `/booking/findFreeRooms?startDate=${startDate}`;

    path += endDate ? `&endDate=${endDate}` : "";
    
    path += people ? `&people=${people}` : "";
    
    path += rooms ? `&rooms=${rooms}` : "";
  
    appFetch(path, config('GET'), onSuccess);

}

export const findTariffsByFreeRoom = (sDate, eDate, roomTypeId, onSuccess) => {
    const startDate = (new Date(sDate)).getTime();
    const endDate = (new Date(eDate)).getTime()
    
    let path = `/booking/findTariffsByFreeRoom?startDate=${startDate}`;

    path += endDate ? `&endDate=${endDate}` : "";
    
    path += roomTypeId ? `&roomTypeId=${roomTypeId}` : "";
    
    console.log(path)
    appFetch(path, config('GET'), onSuccess);

}



export const findSaleRoomTariffsByFreeRoom = (sDate, eDate, roomTypeId, tariffId, onSuccess) => {

    const startDate = (new Date(sDate)).getTime();
    const endDate = (new Date(eDate)).getTime()

    let path = `/booking/findSaleRoomTariffsByFreeRoom?startDate=${startDate}`;

    path += endDate ? `&endDate=${endDate}` : "";
    
    path += roomTypeId ? `&roomTypeId=${roomTypeId}` : "";

    path += tariffId ? `&tariffId=${tariffId}` : "";
    
    console.log(path)
    appFetch(path, config('GET'), onSuccess);

}

export const makeBooking = (bookingRoomSummarys, sDate, eDate, name, surname, phone, email, petition, onSuccess, onErrors) => {

    const startDate = (new Date(sDate)).getTime();
    const endDate = (new Date(eDate)).getTime()
    let path = `/booking/makeBooking`;

    console.log(startDate)
    console.log(endDate)

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
    
    const min = Date.parse(minDate);
    const max = Date.parse(maxDate);

    path += dateType ? `&dateType=${dateType}` : "";
    path += minDate ? `&minDate=${min}` : "";
    path += maxDate ? `&maxDate=${max}` : "";
    path += keywords.length > 0 ? `&keywords=${keywords}` : "";
    
    appFetch(path, config('GET'), onSuccess);
    
}   