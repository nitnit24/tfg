import * as actionTypes from './actionTypes';
import * as selectors from './selectors';
import backend from '../../backend';

export const addTariff = (tariff, onSuccess, 
    onErrors) => backend.tariffService.addTariff(tariff,onSuccess,onErrors)

    const findAllTariffsCompleted = tariffs => ({
        type: actionTypes.FIND_ALL_TARIFFS_COMPLETED,
        tariffs
    }); 
    
    export const findAllTariffs = () => (dispatch, getState) => {
    
         const tariffs = selectors.getTariffs(getState());
    
         if (!tariffs) {
    
            backend.tariffService.findAllTariffs(
                tariffs => dispatch(findAllTariffsCompleted(tariffs))
            );
            
         }
    
    }