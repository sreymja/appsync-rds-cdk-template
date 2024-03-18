import KeyboardDoubleArrowUpIcon from '@mui/icons-material/KeyboardDoubleArrowUp';
import KeyboardDoubleArrowDownIcon from '@mui/icons-material/KeyboardDoubleArrowDown';
import KeyboardDoubleArrowRightIcon from '@mui/icons-material/KeyboardDoubleArrowRight';

type PriorityIconProps = {
    id: number
}
export default function PriorityIcon(props: PriorityIconProps) {
    switch (props.id) {
        case 1:
            return <KeyboardDoubleArrowUpIcon titleAccess={"high priority"} aria-label="high priority"/>
        case 3:
            return <KeyboardDoubleArrowDownIcon titleAccess={"low priority"} aria-label="low priority"/>
        default:
            return <KeyboardDoubleArrowRightIcon titleAccess={"std priority"} aria-label="std priority"/>
    }
}