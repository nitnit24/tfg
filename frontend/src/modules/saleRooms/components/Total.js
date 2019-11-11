import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';


import {Errors} from '../../common';

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



    render() {

        const { showModal } = this.state;

        //const list = this.props.list;
 

        return (
            <div className=" border rounded p-4">
                <h5 class="lead"><b>
                    <FormattedMessage id="project.saleRooms.Total.total"/>
                </b></h5>
                <div className= "m-3">
                    <h4>00,00€</h4>
                </div>
                <div className= "row justify-content-end">
                    <button type="submit" className="btn btn-dark disabled" >
                        <FormattedMessage id="project.global.buttons.continue"/>
                    </button>
                </div>
            </div>
                     
         

        );

    }

}


Total.propTypes = {
     //list: PropTypes.array.isRequired,
     history: PropTypes.object.isRequired
}

export default Total;