import React from 'react';
import {connect} from 'react-redux';
import DayItem from './DayItem';
import FreeRoomsDayItem from './FreeRoomsDayItem';
import TariffDayItem from './TariffDayItem';

import * as selectors from '../selectors';

import '../dailyPanel.css'

import {FormattedMessage} from 'react-intl';


class TablePanel extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
          
        };

      }

    render(){
        
        const roomTables = this.props.roomTables;

        if (roomTables === null) {
            return (
                <div>
                </div>
            );
        }

        return (
             <div>
                {roomTables.map(roomTable => 
                    <table className="table table-bordered">
                        <thead>
                            <tr>
                                <th className = "p-1 room text-center" scope="col" style={{width: '8%'}}> 
                                    <span className = " m-1 text-panel">    
                                        {roomTable.roomTypeName}   
                                    </span> 
                                </th>
                                    {roomTable.roomTableDays.map(roomTableDay => 
                                        <DayItem 
                                            key={roomTable.roomTableDays.indexOf(roomTableDay)}
                                             item={roomTableDay.day} 
                                             pos={roomTable.roomTableDays.indexOf(roomTableDay)}
                                        />
                                   
                                    )} 
                            </tr>
                        </thead>
                    
                        <tbody>
                            <tr>
                                <td className= "p-0" style={{width: '8%'}}> 
                                    <span  className = " m-1 text-panel"><FormattedMessage id="project.dailyPanel.dailyPanel.freeRooms"/></span>
                                </td>
                                {roomTable.roomTableDays.map(roomTableDay => 
                                        <FreeRoomsDayItem
                                            key={roomTable.roomTableDays.indexOf(roomTableDay)}
                                             day={roomTableDay.day}
                                             roomTypeId={roomTable.roomTypeId} 
                                             freeRooms={roomTableDay.freeRooms}
                                        />
                                     )} 
                            </tr>
                            {roomTable.tariffs.map(tariff => 
                                <tr key={roomTable.tariffs.indexOf(tariff)}>
                                    <td className = "p-0" style={{width: '8%'}}>
                                        <span className = "m-1 text-panel ">{tariff.name}</span>
                                    </td>
                                    {roomTable.roomTableDays.map(roomTableDay => 
                                        <TariffDayItem
                                        key={roomTable.roomTableDays.indexOf(roomTableDay)}
                                        day={roomTableDay.day} 
                                        roomTypeId={roomTable.roomTypeId}  
                                        tariffId={roomTableDay.roomTableTariffs[roomTable.tariffs.indexOf(tariff)].tariffId}
                                        price={roomTableDay.roomTableTariffs[roomTable.tariffs.indexOf(tariff)].price}
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
    roomTables: selectors.getRoomTables(state),
    
});

const mapDispatchToProps = {

};

TablePanel = connect(
    mapStateToProps, mapDispatchToProps)(TablePanel);

export default TablePanel;