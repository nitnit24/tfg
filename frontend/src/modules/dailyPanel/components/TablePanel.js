import React from 'react';
import {connect} from 'react-redux';
import DayItem from './DayItem';
import FreeRoomsDayItem from './FreeRoomsDayItem';
import TariffDayItem from './TariffDayItem';

import roomTypes from '../../roomTypes';
import tariffs from '../../tariffs';
import * as selectors from '../selectors';

import '../dailyPanel.css'

import {FormattedMessage} from 'react-intl';


class TablePanel extends React.Component {

    componentDidMount() {
  
        this.props.findAllTariffs();
        this.props.findAllRoomTypes();

    }

    constructor(props) {
        super(props);
        this.state = {
            days: []
        };
      }

    getCalendar(){
        const days = new Array(31);

        for(var i=0;i<31;i++){
            days[i] = new Date();
            days[i].setDate(days[i].getDate()+i);
        }
        return days;
    }

    getCalendarDate(date){
        const res = date.split('-',3);
        const days = new Array(31);

        for(var i=0;i<31;i++){
            days[i] = new Date(res[0],res[1]-1, res[2]);
            days[i].setDate(days[i].getDate()+i);
        }
        return days;
    }

    getHoli() {
        alert('clicked');
    }

    render(){
        var days = [];
        if (this.props.datePanel === ''){
            days = this.getCalendar();
        }
        else
        {
            days = this.getCalendarDate(this.props.datePanel);
        }
        
        const tariffs = this.props.tariffs;
        const roomTypes = this.props.roomTypes;
        

        return (
             <div>
                {roomTypes.map(roomType => 
                    <table className="table table-bordered">
                        <thead>
                            <tr>
                                <th className = "p-1 room text-center" scope="col" style={{width: '8%'}}> 
                                    <span className = " m-1 text-panel">    
                                        {roomType.name}   
                                    </span> 
                                </th>
                                    {days.map(day => 
                                        <DayItem 
                                            key={days.indexOf(day)} item={day} pos ={days.indexOf(day)}
                                        />
                                     )} 
                            </tr>
                        </thead>
                    
                        <tbody>
                            <tr>
                                <td className= "p-0" style={{width: '8%'}}> 
                                    <span  className = " m-1 text-panel"><FormattedMessage id="project.dailyPanel.dailyPanel.freeRooms"/></span>
                                </td>
                                {days.map(day => 
                                        <FreeRoomsDayItem
                                            key={days.indexOf(day)} day={day} roomTypeId={roomType.id}
                                        />
                                     )} 
                            </tr>
                            {tariffs.map(tariff => 
                                <tr key={tariffs.indexOf(tariff)}>
                                    <td className = "p-0" style={{width: '8%'}}>
                                        <span className = "m-1 text-panel ">{tariff.name}</span>
                                    </td>
                                    {days.map(day => 
                                        <TariffDayItem
                                            key={days.indexOf(day)} day={day} roomTypeId={roomType.id} tariffId={tariff.id}
                                        />
                                     )} 
                                </tr>
                            )}
                        </tbody>
                    
                </table>
                )}

                    
            </div>
        );
    }
}

const mapStateToProps = (state) => ({
    datePanel: selectors.getDate(state),
    roomTypes : roomTypes.selectors.getRoomTypes(state),
    tariffs : tariffs.selectors.getTariffs(state),
    
});

const mapDispatchToProps = {
    findAllRoomTypes: roomTypes.actions.findAllRoomTypes,
    findAllTariffs: tariffs.actions.findAllTariffs,
};

export default connect(mapStateToProps, mapDispatchToProps)(TablePanel);