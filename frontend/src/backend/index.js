import {init} from './appFetch';
import * as userService from './userService';
import * as tariffService from './tariffService';

export {default as NetworkError} from "./NetworkError";

export default {init, userService, tariffService};
