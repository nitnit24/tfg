import React from 'react';
import PropTypes from 'prop-types';
import * as actions from '../actions';
import * as selectors from '../selectors';
import {connect} from 'react-redux';

const initialState = {
    backendErrors: null
};

class TariffItem extends React.Component {

    
    constructor(props) {
        super(props);
    
        this.state = initialState;

      }

  
    handleRemoveItem(item) {
       this.props.removeTariff(item,
        () => {
            this.props.onBackendErrors(null);
        }, 
        backendErrors => {
            this.props.onBackendErrors(backendErrors);
        });
    
    }

    handleClick(){
        this.props.findTariffById(this.props.item.id,
            () => this.props.history.push('/tariffs/tariff-update'),
            errors => this.setBackendErrors(errors));

    }
    
    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }


    render() {

        const item = this.props.item;

        return (
            <tr>                   
                <td>{item.code}</td>
                <td>{item.name}</td>
                <td>{item.description}</td>
                <td> 
                <button type="button" className="btn btn-light btn-sm"
                            onClick={() => this.handleRemoveItem(item)}>
                            <span className="fas fa-trash-alt"></span>
                </button>
                </td>
                <td> 
                <button type="button" className="btn btn-light btn-sm"
                            onClick={() => this.handleClick()}>
                            <span className="fas fa-edit"></span>
                </button>
                </td>
            </tr>
          
            
        );

    }

}

TariffItem.propTypes = {
    item: PropTypes.object.isRequired,
    onBackendErrors: PropTypes.func,
    removeTariff: PropTypes.func
}

const mapStateToProps = (state) => ({
    tariff : selectors.getTariff(state)
});

const mapDispatchToProps = {
    findTariffById: actions.findTariffById,
    removeTariff: actions.removeTariff
};

export default connect(mapStateToProps, mapDispatchToProps)(TariffItem);
