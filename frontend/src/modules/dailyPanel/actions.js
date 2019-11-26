import * as actionTypes from './actionTypes';
import backend from '../../backend';


const addDateCompleted = date => ({
    type: actionTypes.ADD_DATE_COMPLETED,
    date
});
      

export const addDate = (date) => (dispatch) => {
    dispatch(addDateCompleted(date));
}
