import React from 'react';
import {FormattedNumber} from 'react-intl';


import {SimpleModal} from '../../common';
import '../../styles.css';
import BookingDayItem from './BookingDayItem';

class BookingRoomItem extends React.Component {

    constructor(props) {

        super(props);

        this.state = {
            showModal: false
          };

    }

    handleToggleModal() {
        this.setState({ showModal: !this.state.showModal });
    }

    render() {
        const { showModal } = this.state;
        const room = this.props.room;
        
        return (
            <div>
                <div className= "row">
                    <div className=" col-1"> <span><b>{room.quantity}</b></span></div>
                    <div className=" col-1"> <span>x</span> </div>
                    <div className=" col-3"> <h6 ><b>{room.roomTypeName}</b></h6></div>   
                    <div clasName= " col-3"><h6 className="text-left"><small>({room.roomTypeCapacity} personas)</small></h6></div>
                    <div className= " col-3"> <h6  className="text-center">{room.tariffName}</h6></div>
                    <div className= " col-1"><b> <FormattedNumber value={room.roomTotalPrice}/> € </b></div>
                    <div className= "col-1">
                        <button type="button" className="btn  btn-link btn-sm"
                            onClick={() => this.handleToggleModal()}>
                                <span className="fa fa-angle-down" ></span> 
                        </button>
                    </div>
                </div>
            <div>
         
            {showModal &&
                <SimpleModal onCloseRequest={() => this.handleToggleModal()}>
                &nbsp;
                <div className=" row  justify-content-end">
                    <div className = "col-4 ">
                        <table class="table border rounded ">
                            <thead className = "thead-table">
                                <tr>
                                    <th scope="col text-center ">Fecha</th>
                                    <th scope="col text-center">Precio por noche</th>
                                </tr>
                            </thead>
                            <tbody>
                            {room.bookingDays.map(roomDay =>
                                <BookingDayItem  key={roomDay.id} roomDay={roomDay} />
                            )}
                            </tbody>
                         </table>
                    </div>
                    <div className = "col-2 "></div>
                </div>
                </SimpleModal>}
            </div>
        </div>

        );

    }

}

export default BookingRoomItem ;