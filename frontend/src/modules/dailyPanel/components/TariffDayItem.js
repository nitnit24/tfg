import React from 'react';

import '../dailyPanel.css';
import  backend from '../../../backend';

const initialState = {
    price: '',
    
    backendErrors: null
};

class TariffDayItem extends React.Component {

    constructor(props) {
        super(props);

        this.state = initialState;

        this.handleChange = this.handleChange.bind(this);
    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    
    componentDidMount() {
        this.find();
    }

    componentDidUpdate(prevProps, prevState) {
        // if (this.props.day !== prevProps.day) {
        //     this.find()
        // }
        //if ((this.props.day === prevProps.day) && (this.state.price !== prevState.price) &&
        //   (this.state.price !== ''))
        // {
          //  this.add();
        //}
      }

    componentWillMount() {
        // const day = this.props.day;
        // let date = day.getDate();
        // let month = day.getMonth() + 1;
        // let year = day.getFullYear();
        
        // backend.dailyPanelService.findSaleRoomTariff(this.props.roomTypeId, this.props.roomTypeId,
        //       year + '-'+ month + '-' + date ,
        // saleRoomTariff =>{ console.log (saleRoomTariff),
        //     this.setState({price:saleRoomTariff.price}) 
        //          });
    }
    
    add(){
        console.log("add");
        const day = this.props.day ;
        const roomTypeId = this.props.roomTypeId;
        const tariffId = this.props.tariffId;
        const price = this.state.price;
        console.log(price),
        console.log(day),
        console.log(roomTypeId),
        console.log(tariffId)
        backend.dailyPanelService.uploadSaleRoomTariff(price, tariffId, roomTypeId, day,
          //  () =>dispatch(findAllRoomTypes()),
           errors => this.setBackendErrors(errors))
    }

    find(){
        const day = this.props.day;
        let date = day.getDate();
        let month = day.getMonth() + 1;
        let year = day.getFullYear();
        
        backend.dailyPanelService.findSaleRoomTariff(this.props.tariffId, this.props.roomTypeId, 
            year + '-'+ month + '-' + date ,
            saleRoomTariff => this.setState({price:saleRoomTariff.price}),
            errors => { this.setBackendErrors(errors),
                        errors ?  this.setState({price:''}) : "";
            });

 


    }



    handleChange(event) {
        this.setState({price: event.target.value})
  
    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    render(){

        const day = this.props.day ;
        const roomTypeId = this.props.roomTypeId;
        const tariffId = this.props.tariffId;

        return (
            <td className= "p-0" style={{width: '2.6%'}}>
                <input type="text" id="price" className="border-0 table-input text-center" 
                    value={this.state.price}
                    onChange={(e) => this.handleChange(e)} 
                    min= "0"  
                />
            </td>

        );
    }
}



export default TariffDayItem;