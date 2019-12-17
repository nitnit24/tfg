import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';
import Bed from'../bed.png';

import {SimpleModal} from '../../common';

import '../../styles.css';



class DetailsRoom extends React.Component {

    constructor(props) {

        super(props);

        this.state = {
            backendErrors: null,
            showModal: false
        }

    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }

    handleToggleModal() {
        this.setState({ showModal: !this.state.showModal });
    }

    render() {

        const { showModal } = this.state;

        const item = this.props.item;

        return (
                <div className="row m-3">
                    <div className="col-md-3 ">
                        <img src={Bed}  className="img-thumbnail"  alt="Hab"/>
                    </div>
                    <div className="col-md-9">
                        <h5 class="lead"><b>{item.name}</b></h5>
                            <div className= "m-3">
                                <FormattedMessage id="project.saleRooms.SaleRoomItemList.capacity"/>
                                {item.capacity} 
                            </div>
                            <div className= "m-3">
                                <button type="button" className="btn  btn-link btn-sm text-secondary"
                                    onClick={() => this.handleToggleModal()}>
                                <span className="fas fa-plus text-secondary"  ></span> 
                                &nbsp;
                                Details 
                            </button>
                            {showModal &&
                            <SimpleModal onCloseRequest={() => this.handleToggleModal()}>
                                <p>Detalles de la habitación</p>
                            </SimpleModal>
                            }
                            </div>
                    </div>
                </div>      
         

        );

    }

}


DetailsRoom.propTypes = {
     //list: PropTypes.array.isRequired,
     history: PropTypes.object.isRequired
}

export default DetailsRoom;