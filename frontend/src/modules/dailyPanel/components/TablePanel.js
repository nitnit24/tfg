import React from 'react';
import {connect} from 'react-redux';
import DayItem from './DayItem';
import roomTypes from '../../roomTypes';
import tariffs from '../../tariffs';

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

    getDayOfWeek(day){
        switch(day.getDay()+1){
            case 1: return( "lun." );
            case 2: return( "mar." );
            case 3: return( "mié." );
            case 4: return( "jue." );
            case 5: return( "vie." );
            case 6: return( "sáb." );
            case 7: return( "dom." );
        break;      
        }
    }

    getNameMonth(day){
        switch(day.getMonth()+1){
            case 1: return( "ENE." );
            case 2: return( "FEB." );
            case 3: return( "MAR." );
            case 4: return( "ABR." );
            case 5: return( "MAY." );
            case 6: return( "JUN." );
            case 7: return( "JUL." );
            case 8: return( "AGO." );
            case 9: return( "SEP." );
            case 10: return( "OCT." );
            case 11: return( "NOV." );
            case 12: return( "DEC." );
        break;      
        }
    }

    
    render(){

        const days = this.getCalendar();
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
                                        // <DayItem 
                                        //     key={day.getDate()} item={day.getDate()}
                                        // />
                                        <th className = "p-0 panel" scope="col" style={{width: '2.6%'}}>
                                            { (day.getDate() === 1 || days[0] === day) &&
                                            <span className = " m-0 text-panelWeekDay">  {this.getNameMonth(day)}</span>
                                            }
                                            <br></br> 
                                            <span className = " m-0 text-panelWeekDay text-secondary">  {this.getDayOfWeek(day)}</span>
                                            <br></br>
                                            <span className = " m-0 text-panel">  {day.getDate()} </span>
                                        </th>

                                     )} 
                            </tr>
                        </thead>
                    
                        <tbody>
                            <tr>
                                <td className= "p-0" style={{width: '8%'}}> 
                                    <span  className = " m-1 text-panel"><FormattedMessage id="project.dailyPanel.dailyPanel.freeRooms"/></span>
                                </td>
                                {days.map(item => 
                                        <td className = "p-0" style={{width: '2.6%'}}>
                                             <input type="text" id="" className=" border-0 table-input text-center" 
                                                value='0'          
                                             />
                                        </td>
                                     )} 
                            </tr>
                            {tariffs.map(tariff => 
                                <tr>
                                    <td className = "p-0" style={{width: '8%'}}>
                                        <span className = "m-1 text-panel ">{tariff.name}</span>
                                    </td>
                                    {days.map(item => 
                                        <td className= "p-0" style={{width: '2.6%'}}>
                                             <input type="text" id=""className="border-0 table-input text-center" 
                                                value='000'
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

export default connect(mapStateToProps, mapDispatchToProps)(TablePanel);