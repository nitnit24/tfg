import * as actionTypes from './actionTypes';
import backend from '../../backend';


export const addTariff = (tariff, onErrors) => (dispatch) => {

    backend.tariffService.addTariff(tariff,
        () =>dispatch(findAllTariffs()),
        onErrors)
}

export const removeTariff = (tariff, onErrors) => (dispatch) => {
   
    backend.tariffService.removeTariff(tariff,
        () => dispatch(findAllTariffs()),
        onErrors)
}

export const updateTariff = (tariff, onSuccess, onErrors) => (dispatch) => {
   
    backend.tariffService.updateTariff(tariff,
        () => {
            dispatch(findAllTariffs());
            onSuccess();
        },
        onErrors)
}

const findAllTariffsCompleted = tariffs => ({
        type: actionTypes.FIND_ALL_TARIFFS_COMPLETED,
        tariffs
    }); 


export const findAllTariffs = () => (dispatch) => {
        
        backend.tariffService.findAllTariffs(
            tariffs => dispatch(findAllTariffsCompleted(tariffs))
        );
    }

const findTariffByIdCompleted = tariff => ({
    type: actionTypes.FIND_TARIFF_BY_ID_COMPLETED,
    tariff
});
        
export const findTariffById = id => dispatch => {
    backend.tariffService.findTariffById(id,
        tariff => dispatch(findTariffByIdCompleted(tariff)));
}

