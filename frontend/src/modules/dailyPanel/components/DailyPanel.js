import React from 'react';
import {connect} from 'react-redux';
import DayItem from './DayItem';
import roomTypes from '../../roomTypes';
import tariffs from '../../tariffs';

import '../dailyPanel.css'

import {FormattedMessage} from 'react-intl';


class DailyPanel extends React.Component {

    componentDidMount() {
  
        this.props.findAllTariffs();
        this.props.findAllRoomTypes();

    }

    constructor(props) {
        super(props);
        this.state = {
        };
      }

    render(){

        const days = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17] ;
        const tariffs = this.props.tariffs;
        const roomTypes = this.props.roomTypes;

        return (
             <div>
                <h4 className="h4">
                     <FormattedMessage id='project.dailyPanel.dailyPanelTable.title' /> 
                </h4>
                {roomTypes.map(roomType => 
                    <table className="table table-bordered">
                        <thead>
                            <tr class = "header">
                                <th class = "room" scope="col"> 
                                    <span class = "text-panel">    
                                        {roomType.name}   
                                    </span> 
                                </th>
                                    {days.map(day => 
                                        <DayItem 
                                            key={day} item={day}
                                        />
                                     )} 
                            </tr>
                        </thead>
                    
                        <tbody>
                            <tr style={{width: '5%'}}>
                                <td style={{width: '5%'}}> 
                                    <span  class = "text-panel"><FormattedMessage id="project.dailyPanel.dailyPanel.freeRooms"/></span>
                                </td>
                                {days.map(item => 
                                        <td>
                                             <input type="text" id="" className="form-control" 
                                                value='0'
                                                
                                             />
                                        </td>
                                     )} 
                            </tr>
                            {tariffs.map(tariff => 
                                <tr>
                                    <td><span class = "text-panel">{tariff.name}</span></td>
                                    {days.map(item => 
                                        <td >
                                             <input type="text" id=""className="form-control" 
                                                value='0'
                                             />
                                        </td>
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
    roomTypes : roomTypes.selectors.getRoomTypes(state),
    tariffs : tariffs.selectors.getTariffs(state),
    
});

const mapDispatchToProps = {
    findAllRoomTypes: roomTypes.actions.findAllRoomTypes,
    findAllTariffs: tariffs.actions.findAllTariffs,
};

export default connect(mapStateToProps, mapDispatchToProps)(DailyPanel);