import React from 'react';
import {FormattedMessage} from 'react-intl';
import * as selectors from '../selectors';


import {connect} from 'react-redux';

import RoomsList from './RoomsList';

import '../../styles.css';



class Total extends React.Component {

    constructor(props) {

        super(props);

        this.state = {
            backendErrors: null,
           
        }

    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }


    totalPrice(){
        var lista= this.props.summaryRooms;
        const suma = lista.reduce((total, room) =>
            {return total + room.price * room.quantity},0);
       return suma;
    
    }

    render() {

        return (
            <div className=" border rounded p-4">
                <h5 class="lead"><b>
                    <FormattedMessage id="project.saleRooms.Total.total"/>
                </b></h5>
                <div className= "m-3">
                    <h4>{this.totalPrice()}€</h4>
                </div>
                { (this.props.startDate && this.props.endDate && this.props.summaryRooms.length >0) && 
                <div>
                    <div className= "row justify-content-center">
                        <button type="submit" className="btn btn-dark disabled"
                            onClick={() => this.props.history.push('/booking/client-form')} >
                            <FormattedMessage id="project.global.buttons.continue"/>
                        </button>
                    </div>
                    {/* <div className= "m-3">
                        <hr/><hr/>
                        <p>
                            <FormattedDate value={new Date(this.props.startDate)}/>
                            &nbsp;-&nbsp;
                            <FormattedDate value={new Date(this.props.endDate)}/>
                        </p>
                    </div> */}
                </div>
                }
                <RoomsList history={this.props.history} list={this.props.summaryRooms}/>
            </div>
                     
         

        );

    }

}


const mapStateToProps = (state) => ({
    startDate: selectors.getStartDate(state),
    endDate: selectors.getEndDate(state),
    summaryRooms: selectors.getSummaryRooms(state)

});

const mapDispatchToProps = {
};

export default connect (mapStateToProps, mapDispatchToProps)(Total);