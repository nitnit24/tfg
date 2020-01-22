import React from 'react';
import { FormattedDate} from 'react-intl';

import '../../styles.css';


class BookingDayItem extends React.Component {

    constructor(props) {

        super(props);

        this.state = {
            backendErrors: null,
        }

    }

    render() {

        const roomDay = this.props.roomDay;
        
        return (
                <tr>
                    <td>  <span><FormattedDate value={new Date(roomDay.day)}/></span> </td>
                    <td className = "text-center">{roomDay.dayPrice} €</td> 
                </tr>
          

        );

    }

}

export default BookingDayItem ;