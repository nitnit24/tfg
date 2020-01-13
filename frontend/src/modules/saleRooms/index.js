import * as actions from './actions';
import * as actionTypes from './actionTypes';
import reducer from './reducer';
import * as selectors from './selectors';

export {default as SaleRoom} from './components/SaleRoom';
export {default as SaleRoomsFindForm} from './components/SaleRoomsFindForm';
export {default as SaleRoomsItemList} from './components/SaleRoomItemList';
export {default as DetailsRoom} from './components/DetailsRoom';
export {default as TariffsItemList} from './components/TariffsItemList';
export {default as Total} from './components/Total';
export {default as BookingData} from './components/BookingData';
export {default as ClientForm} from './components/ClientForm';
export {default as BookingCompleted} from './components/BookingCompleted';
export {default as BookingFindForm} from './components/BookingFindForm';
export {default as RoomsList} from './components/RoomsList';
export {default as BookingRoomItem} from './components/BookingRoomItem';


export default {actions, actionTypes, reducer, selectors};