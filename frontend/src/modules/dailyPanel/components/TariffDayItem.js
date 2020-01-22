import React from 'react';

import '../dailyPanel.css';
import {connect} from 'react-redux';
import * as actions from '../actions';


const initialState = {
    price: '',
    priceError: '',
    
    backendErrors: null
};

class TariffDayItem extends React.Component {

    constructor(props) {
        super(props);

        this.state = initialState;

        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        this.setState({price: this.props.price});
    }

    add(price){
        const day = this.props.day ;
        const roomTypeId = this.props.roomTypeId;
        const tariffId = this.props.tariffId;

        this.props.uploadTariffPrice(price, tariffId, roomTypeId, day,
        null,
        errors => this.setBackendErrors(errors))
        
    }

    handleChange(event) {
        this.setState({price: event.target.value}, () => {this.validatePrice});

        if((this.props.minPrice <= event.target.value) && (event.target.value <= this.props.maxPrice )){
            this.add(event.target.value);
        }
    }

    validatePrice = () => {
        const { price } = this.state;
        this.setState({
          priceError:
            ((price >= this.props.minPrice && price <= this.props.maxPrice) || !price ) ? null : 'El precio debe estar entre ' + this.props.minPrice + ' y ' + this.props.maxPrice
        });
      }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    render(){
        return (
            <td className= "p-0" style={{width: '2.6%'}}>
                 <input type="text"
                    className={` border-0 table-input text-center  ${this.state.priceError ? ' bg-danger text-white' : ''}`}
                    value={this.state.price}
                    onChange={(e) => this.handleChange(e)} 
                    onBlur={this.validatePrice}
                     data-toggle="tooltip" data-placement="right" title={this.state.priceError} 
                /> 
            </td>
            
        );
    }
}



const mapStateToProps = (state) => ({
});

const mapDispatchToProps = {
    uploadTariffPrice: actions.uploadTariffPrice
};
export default connect( mapStateToProps, mapDispatchToProps)(TariffDayItem);