import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';

import '../../styles.css';

const initialState = {
    backendErrors: null
};

class TariffsItemList extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState

    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }


    render() {

        //const list = this.props.list;

        return (
                <div className=" m-3 row justify-content-center">
                    <div className= "col-4 align-self-end">
                        Con desayuno
                    </div>
                    <div className= "col-3 align-self-end">
                        85€
                    </div>
                    <div className= "col-2 h-100 align-self-end">
                     <span className="text-secondary small"> Cantidad habt.</span>
                        <select class="h-25 form-control " id="Quantity">
                            <option value="0">0 </option>
                            <option value="1">1 </option>
                            <option value="2">2 </option>
                            <option value="3">3 </option>
                            <option value="4">4 </option>
                        </select>
                    </div>
                    <div className= "col-1 align-self-end ">
                        <input type="checkbox" type="radio" className="" id="checkTariff"></input>
                    </div>
                </div>

        );

    }

}


TariffsItemList.propTypes = {
     //list: PropTypes.array.isRequired,
     //history: PropTypes.object.isRequired
}

export default TariffsItemList;