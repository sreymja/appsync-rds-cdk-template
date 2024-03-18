import Moment from 'moment';

export default function renderDate(dt: string | undefined){
    if(dt === undefined) return '';
    Moment.locale('en');
    return Moment(dt).format('DD MMM yy hh:mm')
}