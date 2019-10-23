import {init} from './appFetch';
import * as userService from './userService';
import * as tariffService from './tariffService';
import * as roomTypeService from './roomTypeService';

export {default as NetworkError} from "./NetworkError";

export default {init, userService, tariffService, roomTypeService};
