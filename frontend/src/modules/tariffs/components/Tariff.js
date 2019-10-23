import React from 'react';
import {connect} from 'react-redux';

import TariffForm from './TariffForm';
import TariffItemList from './TariffItemList';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {SimpleModal} from '../../common';
import {FormattedMessage} from 'react-intl';


class Tariff extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          showModal: false,
        };
      }
    
    handleToggleModal() {
        this.setState({ showModal: !this.state.showModal });
    }

    componentDidMount() {
        this.props.findTariffs();
    }

    render(){

        const { showModal } = this.state;

        return (
             <div>
                 <div>
                    <button type="button" className="btn  btn-link btn-sm"
                    onClick={() => this.handleToggleModal()}>
                     <span className="fas fa-plus" ></span> 
                      &nbsp;
                     <FormattedMessage id="project.tariffs.TariffForm.title"/>
                    </button>

                    {showModal &&
                    <SimpleModal onCloseRequest={() => this.handleToggleModal()}>
                        <TariffForm history={this.props.history} />
                    </SimpleModal>}
                    </div>
                &nbsp;
                &nbsp;
                <TariffItemList list={this.props.tariffs} history={this.props.history}/> 
            </div>
        );
    }
}

const mapStateToProps = state => ({
    tariffs: selectors.getTariffs(state)
});


const mapDispatchToProps = {
    findTariffs : actions.findAllTariffs
}

export default connect(mapStateToProps,mapDispatchToProps)(Tariff);
