import React from 'react';
import PropTypes from 'prop-types';


class TariffItem extends React.Component {

    constructor(props) {

        super(props);

    }

    render() {

        const item = this.props.item;

        return (
            <tr>                   
                <td>{item.code}</td>
                <td>{item.name}</td>
                <td> 
                <button type="button" className="btn btn-light btn-sm"
                            //onClick={() => this.handleRemoveItem()}
                            >
                            <span className="fas fa-trash-alt"></span>
                </button>
                </td>
                <td> 
                <button type="button" className="btn btn-light btn-sm"
                            //onClick={() => this.handleRemoveItem()}
                            >
                            <span className='fas fa-edit'></span>
                </button>
                </td>
            </tr>
        );

    }

}

TariffItem.propTypes = {
    item: PropTypes.object.isRequired,
    onBackendErrors: PropTypes.func
}

export default TariffItem;
