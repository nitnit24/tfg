import * as actionTypes from './actionTypes';


const addDateCompleted = date => ({
    type: actionTypes.ADD_DATE_COMPLETED,
    date
});
      

export const addDate = (date) => (dispatch) => {
    dispatch(addDateCompleted(date));
}
