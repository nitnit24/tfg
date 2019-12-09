import React from 'react';
import {connect} from 'react-redux';

import {FormattedMessage} from 'react-intl';
import '../dailyPanel.css';




class DayItem extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
        };
      }

      getDayOfWeek(day){
        switch(day.getDay()){
            case 1: return( "lun." );
            case 2: return( "mar." );
            case 3: return( "mié." );
            case 4: return( "jue." );
            case 5: return( "vie." );
            case 6: return( "sáb." );
            case 0: return( "dom." );
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

        const item = this.props.item ;
        const pos = this.props.pos;

        return (
            <th className = "p-0 panel" scope="col" style={{width: '2.6%'}}>
                { (item.getDate() === 1 || pos === 0) && 
                <span className = " m-0 text-panelWeekDay">  {this.getNameMonth(item)}</span>
                }
                <br></br> 
                <span className = " m-0 text-panelWeekDay text-secondary">  {this.getDayOfWeek(item)}</span>
                <br></br>
                <span className = " m-0 text-panel">  {item.getDate()} </span>
            </th>

        );
    }
}



export default DayItem;