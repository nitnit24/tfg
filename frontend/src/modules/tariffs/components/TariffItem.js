import React from 'react';
import PropTypes from 'prop-types';
import {Link} from 'react-router-dom';


class TariffItem extends React.Component {

    
    constructor(props) {
        super(props);
    
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
                <td> 
                <button type="button" className="btn btn-light btn-sm"
                            onClick={() => this.handleRemoveItem(item)}>
                            <span className="fas fa-trash-alt"></span>
                </button>
                </td>
                <td> 
                <Link className="dropdown-item" to = {`/tariffs/tariff-update/${item.id}`}>
                        <span className='fas fa-edit'></span>
                </Link>
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

export default (TariffItem);
